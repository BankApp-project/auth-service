package online.bankapp.authservice.model.vo;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

import java.util.Objects;

/**
 * A Value Object representing a One-Time Password.
 * It is immutable and ensures the value is always in a valid format.
 */
public final class OTP {

    private static final String OTP_PATTERN = "^[a-zA-Z0-9]+$";

    @Value("${app.otp.length}")
    private int MAX_LENGTH;

    @Getter
    private final String value;

    public OTP(String value) {
        if (value == null) {
            throw new IllegalArgumentException("OTP value must be non-null.");
        }
        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("OTP max length exceeded. MAX_LENGTH: " + MAX_LENGTH);
        }
        if (!value.matches(OTP_PATTERN)) {
            throw new IllegalArgumentException("OTP value must be alphanumeric string.");
        }
        this.value = value;
    }

    public OTP(int value) {
        this.value = String.valueOf(value);
    }

    @Override
    public String toString() {
        // Avoid logging the actual OTP value in production for security.
        return "OTP[value=******]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OTP otp = (OTP) o;
        return Objects.equals(value, otp.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public int length() {
        return value.length();
    }
}
