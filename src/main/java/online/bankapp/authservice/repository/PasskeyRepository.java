package online.bankapp.authservice.repository;

import online.bankapp.authservice.model.Passkey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.web.webauthn.api.Bytes;
import org.springframework.security.web.webauthn.api.CredentialRecord;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PasskeyRepository extends JpaRepository<Passkey, UUID> {

    void deleteByCredentialId(Bytes credentialId);

    Optional<Passkey> findByCredentialId(Bytes credentialId);

    List<CredentialRecord> findAllByUserEntityUserId(Bytes userEntityUserId);
}
