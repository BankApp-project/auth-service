package online.bankapp.auth.infrastructure.messaging.outbox;

import lombok.Getter;

public enum AggregateType {
    EMAIL_VERIFICATION_ATTEMPT("email.verification");

    @Getter
    private final String name;

    AggregateType(String value) {
        this.name = value;
    }

    public String toString() {
        return name;
    }
}
