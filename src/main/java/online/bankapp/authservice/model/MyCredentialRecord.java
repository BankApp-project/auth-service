package online.bankapp.authservice.model;

import com.webauthn4j.credential.CredentialRecord;
import com.webauthn4j.data.AuthenticatorTransport;
import com.webauthn4j.data.attestation.authenticator.AttestedCredentialData;
import com.webauthn4j.data.attestation.statement.AttestationStatement;
import com.webauthn4j.data.client.CollectedClientData;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;

@Getter
public class MyCredentialRecord implements CredentialRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false, updatable = false)
    private byte[] credentialId;

    @Setter
    @Column(nullable = false)
    private long counter;

    private AttestationStatement attestationStatement;

    private CollectedClientData clientData;

    private AttestedCredentialData attestedCredentialData;

    private Set<AuthenticatorTransport> transports;

    @Setter
    private boolean uvInitialized;

    @Setter
    private boolean backupEligible;

    @Setter
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
}
