package online.bankapp.authservice.event;

import online.bankapp.authservice.model.vo.EmailAddress;

import java.time.Instant;

public record UserVerificationReqEvent(EmailAddress email, Instant timestamp) {
    public UserVerificationReqEvent(EmailAddress email) {
        this(email, Instant.now());
    }
}
