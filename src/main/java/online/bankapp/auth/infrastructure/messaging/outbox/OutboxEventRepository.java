package online.bankapp.auth.infrastructure.messaging.outbox;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OutboxEventRepository extends JpaRepository<OutboxEvent, UUID> {

    OutboxEvent getOutboxEventByPublished(boolean value);
}
