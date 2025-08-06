package online.bankapp.authservice.event;

import online.bankapp.authservice.model.vo.EmailAddress;

import java.time.Instant;
import java.util.UUID;

public record UserVerificationReqEvent(EmailAddress email, Instant timestamp, UUID id) {
    public UserVerificationReqEvent(EmailAddress email) {
        this(email, Instant.now(), UUID.randomUUID());
    }
}