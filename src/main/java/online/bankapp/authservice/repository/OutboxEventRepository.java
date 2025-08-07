package online.bankapp.authservice.repository;

import online.bankapp.authservice.model.OutboxEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OutboxEventRepository extends JpaRepository<OutboxEvent, UUID> {

    OutboxEvent getOutboxEventByPublished(boolean value);
}
