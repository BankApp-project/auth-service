package online.bankapp.authservice.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "passkeys", indexes = {
        // This index is crucial for fast lookups during login.
        @Index(name = "idx_credential_id", columnList = "credentialId", unique = true)
})
public class Passkey {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, unique = true)
    private byte[] credentialId;

    //in service layer we will have create method with CredentialRecord and User parameters
    @Column(nullable = false, unique = false)
    private byte[] userHandle;

    @Column(nullable = false, unique = false)
    private String username;

    @Lob // Use @Lob for large binary objects
    @Column(nullable = false, length = 1024)
    private byte[] publicKey;

    @Column(nullable = false)
    private long signatureCount;

    @Column(nullable = false)
    private boolean uvInitialized;

    @Column(nullable = false)
    private boolean backupEligible;

    @Column(nullable = false)
    private boolean backupState;

    // Store the set of transports as a simple comma-separated string.
    private String transports;
}