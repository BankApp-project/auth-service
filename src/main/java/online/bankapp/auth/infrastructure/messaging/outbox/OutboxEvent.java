package online.bankapp.auth.infrastructure.messaging.outbox;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Entity
@Table(name = "outbox")
public class OutboxEvent {

    @Id
    private UUID id = UUID.randomUUID();

    @Column(nullable = false)
    private Instant timestamp = Instant.now();    // Time of the event

    @Column(nullable = false)
    private String aggregateType; // e.g., "Order", "Customer", "User", "EmailVerificationAttempt"

    @Column(nullable = false)
    private UUID aggregateId;   // The ID of the specific order or attempt

    @Column(nullable = false)
    private String eventType;     // e.g., "created"

    @Column(columnDefinition = "JSON", nullable = false)
    private String payload;       // The event data as a JSON string

    @Setter
    private boolean published = false; // Processing status

    protected OutboxEvent() {
        //For JPA
    }

    public OutboxEvent(AggregateType aggregateType, UUID aggregateId, String eventType, String payload) {
        this.aggregateType = aggregateType.getName();
        this.aggregateId = aggregateId;
        this.eventType = eventType;
        this.payload = payload;
    }

    /*
     *    equals() and hashCode() won't work with not yet persisted objects.
     * It shouldnt be a problem, since we wont operate on not saved objects.
     */
    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof OutboxEvent that)) return false;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
