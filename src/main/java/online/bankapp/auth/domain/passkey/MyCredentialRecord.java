package online.bankapp.auth.domain.passkey;

import com.webauthn4j.credential.CredentialRecord;
import com.webauthn4j.data.AuthenticatorTransport;
import com.webauthn4j.data.attestation.authenticator.AttestedCredentialData;
import com.webauthn4j.data.attestation.statement.AttestationStatement;
import com.webauthn4j.data.client.CollectedClientData;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.util.Set;

@Getter
@Entity
public class MyCredentialRecord implements CredentialRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false, updatable = false)
    private byte[] credentialId;

    @Column(unique = false, nullable = false, updatable = false)
    private byte[] userHandle;

    @Setter
    @Column(nullable = false)
    private long counter;

    private AttestationStatement attestationStatement;

    private CollectedClientData clientData;

    private AttestedCredentialData attestedCredentialData;

    @Column(nullable = false, columnDefinition = "VARCHAR")
    private Set<AuthenticatorTransport> transports;

    private boolean uvInitialized;

    private boolean backupEligible;

    private boolean backedUp;

    private Instant createdAt;

    private Instant lastUsed;

    public MyCredentialRecord(byte[] credentialId,
                              long counter,
                              AttestationStatement attestationStatement,
                              CollectedClientData clientData,
                              AttestedCredentialData attestedCredentialData,
                              Set<AuthenticatorTransport> transports,
                              boolean uvInitialized,
                              boolean backupEligible,
                              boolean backedUp) {
        this.credentialId = credentialId;
        this.counter = counter;
        this.attestationStatement = attestationStatement;
        this.clientData = clientData;
        this.attestedCredentialData = attestedCredentialData;
        this.transports = transports;
        this.uvInitialized = uvInitialized;
        this.backupEligible = backupEligible;
        this.backedUp = backedUp;
    }

    protected MyCredentialRecord() {
        //For JPA
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = Instant.now();
        this.lastUsed = Instant.now();
        this.credentialId = this.attestedCredentialData.getCredentialId();
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastUsed = Instant.now();
    }

    // TODO check it
    // For some reason i can't use @Getter/@Setter for this fields. Compilator wont allow that. Dont know why.

    @Override
    public @Nullable Boolean isUvInitialized() {
        return uvInitialized;
    }

    @Override
    public void setUvInitialized(boolean value) {
        this.uvInitialized = value;
    }

    @Override
    public @Nullable Boolean isBackupEligible() {
        return backupEligible;
    }

    @Override
    public void setBackupEligible(boolean value) {
        this.backupEligible = value;
    }

    @Override
    public @Nullable Boolean isBackedUp() {
        return this.backedUp;
    }

    @Override
    public void setBackedUp(boolean value) {
        this.backedUp = value;
    }
}
