package online.bankapp.authservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.security.web.webauthn.api.*;

import java.time.Instant;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Table(name = "passkeys", indexes = {
        // This index is crucial for fast lookups during login.
        @Index(name = "idx_credential_id", columnList = "credential_id", unique = true),
        // Add index for userEntityUserId queries
        @Index(name = "idx_user_entity_user_id", columnList = "user_entity_user_id")
})
public class Passkey implements CredentialRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "credential_id", nullable = false, columnDefinition = "BYTEA")
    private Bytes credentialId;

    @Column(name = "credential_type", nullable = false, columnDefinition = "VARCHAR")
    private PublicKeyCredentialType credentialType;

    @Column(name = "public_key", nullable = false, columnDefinition = "BYTEA")
    private PublicKeyCose publicKey;

    @Column(name = "transports", length = 255, columnDefinition = "VARCHAR")
    private Set<AuthenticatorTransport> transports;

    @Column(name = "uv_initialized")
    private boolean uvInitialized;

    @Column(name = "backup_eligible")
    private boolean backupEligible;

    @Column(name = "backup_state")
    private boolean backupState;

    @Column(name = "user_entity_user_id", nullable = false, columnDefinition = "BYTEA")
    private Bytes userEntityUserId;

    @Column(name = "attestation_object", columnDefinition = "BYTEA")
    private Bytes attestationObject;

    @Column(name = "attestation_client_data_json", columnDefinition = "BYTEA")
    private Bytes attestationClientDataJSON;

    @Column(name = "label")
    private String label;

    @Column(name = "last_used")
    private Instant lastUsed;

    @Column(name = "created", nullable = false)
    private Instant created;

    @Column(name = "signature_count")
    private long signatureCount;

    public Passkey(PublicKeyCredentialType credentialType,
                   Bytes credentialId,
                   PublicKeyCose publicKey,
                   Set<AuthenticatorTransport> transports,
                   boolean uvInitialized,
                   boolean backupEligible,
                   boolean backupState,
                   Bytes userEntityUserId,
                   Bytes attestationObject,
                   Bytes attestationClientDataJSON,
                   String label,
                   Instant lastUsed,
                   Instant created,
                   long signatureCount) {
        this.credentialId = credentialId;
        this.credentialType = credentialType;
        this.publicKey = publicKey;
        this.transports = transports;
        this.uvInitialized = uvInitialized;
        this.backupEligible = backupEligible;
        this.backupState = backupState;
        this.userEntityUserId = userEntityUserId;
        this.attestationObject = attestationObject;
        this.attestationClientDataJSON = attestationClientDataJSON;
        this.label = label;
        this.lastUsed = lastUsed;
        this.created = created;
        this.signatureCount = signatureCount;
    }

    protected Passkey() {
        // For JPA
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Passkey passkey = (Passkey) o;
        return getId() != null && Objects.equals(getId(), passkey.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}