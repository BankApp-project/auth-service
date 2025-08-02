package online.bankapp.authservice.repository;


import lombok.RequiredArgsConstructor;
import online.bankapp.authservice.model.Passkey;
import org.springframework.security.web.webauthn.api.Bytes;
import org.springframework.security.web.webauthn.api.CredentialRecord;
import org.springframework.security.web.webauthn.management.UserCredentialRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaUserCredentialRepository implements UserCredentialRepository {

    private final PasskeyRepository repository;

    @Override
    public void delete(Bytes credentialId) {
        repository.deleteByCredentialId(credentialId);
    }

    @Override
    public void save(CredentialRecord credentialRecord) {
        var passkey = new Passkey(
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
        repository.save(passkey);
    }

    @Override
    public CredentialRecord findByCredentialId(Bytes credentialId) {
        Optional<Passkey> passkey = repository.findByCredentialId(credentialId);

        return passkey.orElse(null);
    }

    @Override
    public List<CredentialRecord> findByUserId(Bytes userId) {
        return repository.findAllByUserEntityUserId(userId);
    }
}

