package online.bankapp.auth.application.command;

import online.bankapp.auth.domain.user.vo.EmailAddress;

import java.time.Instant;
import java.util.UUID;

public record RequestEmailVerification(EmailAddress email, Instant timestamp, UUID id) {
    public RequestEmailVerification(EmailAddress email) {
        this(email, Instant.now(), UUID.randomUUID());
    }
}