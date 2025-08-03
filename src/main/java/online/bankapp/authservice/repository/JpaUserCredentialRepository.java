package online.bankapp.authservice.repository;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.bankapp.authservice.model.Passkey;
import org.springframework.security.web.webauthn.api.Bytes;
import org.springframework.security.web.webauthn.api.CredentialRecord;
import org.springframework.security.web.webauthn.management.UserCredentialRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class JpaUserCredentialRepository implements UserCredentialRepository {

    private final PasskeyRepository repository;

    @Transactional
    @Override
    public void delete(Bytes credentialId) {
        log.info("Deleting credential with ID: {}", credentialId);
        repository.deleteByCredentialId(credentialId);
        log.debug("Successfully deleted credential with ID: {}", credentialId);
    }

    @Transactional
    @Override
    public void save(CredentialRecord credentialRecord) {
        log.info("Saving credential record with ID: {}, user ID: {}",
                credentialRecord.getCredentialId(),
                credentialRecord.getUserEntityUserId());


        var passkeyOpt = repository.findByCredentialId(credentialRecord.getCredentialId());
        if (passkeyOpt.isPresent()) {
            updateExistingUser(credentialRecord, passkeyOpt.get());
            return;
        }
        repository.save(createPasskey(credentialRecord));
        log.debug("Successfully saved credential with ID: {}", credentialRecord.getCredentialId());
    }

    private void updateExistingUser(CredentialRecord credentialRecord, Passkey passkey) {
        log.debug("Credential already present. Updating...");
        passkey.setLastUsed(credentialRecord.getLastUsed());
        //TODO some check for signatureCount should be done - for safety reasons
        passkey.setSignatureCount(credentialRecord.getSignatureCount());
        log.debug("Credential updated!");
    }

    private Passkey createPasskey(CredentialRecord credentialRecord) {
        return new Passkey(
                credentialRecord.getCredentialType(),
                credentialRecord.getCredentialId(),
                credentialRecord.getPublicKey(),
                credentialRecord.getTransports(),
                credentialRecord.isUvInitialized(),
                credentialRecord.isBackupEligible(),
                credentialRecord.isBackupState(),
                credentialRecord.getUserEntityUserId(),
                credentialRecord.getAttestationObject(),
                credentialRecord.getAttestationClientDataJSON(),
                credentialRecord.getLabel(),
                credentialRecord.getLastUsed(),
                credentialRecord.getCreated(),
                credentialRecord.getSignatureCount()
        );
    }

    @Override
    public CredentialRecord findByCredentialId(Bytes credentialId) {
        log.info("Finding credential by ID: {}", credentialId);
        Optional<Passkey> passkey = repository.findByCredentialId(credentialId);

        if (passkey.isPresent()) {
            log.debug("Found credential with ID: {}", credentialId);
        } else {
            log.debug("No credential found with ID: {}", credentialId);
        }

        return passkey.orElse(null);
    }

    @Override
    public List<CredentialRecord> findByUserId(Bytes userId) {
        log.info("Finding credentials for user ID: {}", userId);
        List<CredentialRecord> credentials = repository.findAllByUserEntityUserId(userId);
        log.debug("Found {} credentials for user ID: {}", credentials.size(), userId);
        return credentials;
    }
}

