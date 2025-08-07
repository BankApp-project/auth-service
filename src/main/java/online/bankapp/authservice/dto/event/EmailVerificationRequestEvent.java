package online.bankapp.authservice.dto.event;

import online.bankapp.authservice.model.vo.EmailAddress;

import java.time.Instant;
import java.util.UUID;

public record EmailVerificationRequestEvent(EmailAddress email, Instant timestamp, UUID id) {
    public EmailVerificationRequestEvent(EmailAddress email) {
        this(email, Instant.now(), UUID.randomUUID());
    }
}