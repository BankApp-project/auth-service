package online.bankapp.authservice.model;

import lombok.Getter;

public enum AggregateType {
    USER_VERIFICATION_ATTEMPT("user-verification-attempt");

    @Getter
    private final String name;

    AggregateType(String value) {
        this.name = value;
    }

    public String toString() {
        return name;
    }
}
