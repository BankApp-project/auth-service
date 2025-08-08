package online.bankapp.authservice.dto.event;

import online.bankapp.authservice.model.vo.EmailAddress;

import java.time.Instant;
import java.util.UUID;

public record EmailVerificationRequest(EmailAddress email, Instant timestamp, UUID id) {
    public EmailVerificationRequest(EmailAddress email) {
        this(email, Instant.now(), UUID.randomUUID());
    }
}