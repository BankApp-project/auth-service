package online.bankapp.authservice.model.vo;

import lombok.Getter;

import java.util.Objects;

/**
 * A Value Object representing a One-Time Password.
 * It is immutable and ensures the value is always in a valid format.
 */
public final class OTP {

    @Getter
    private final String value;

    public OTP(String value) {
        if (value == null) { // Ensures it's all digits
            throw new IllegalArgumentException("OTP value must be non-null string.");
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
