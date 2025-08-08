package online.bankapp.auth.domain.otp.model;

import lombok.Getter;

import java.util.Objects;

/**
 * A Value Object representing a One-Time Password.
 * It is immutable and ensures the value is always in a valid format.
 */
public final class OTP {

    private static final String OTP_PATTERN = "^[a-zA-Z0-9]+$";

    @Getter
    private final String value;

    @Getter
    private final String key;

    public OTP(String value, String key) {
        if (key == null) {
            throw new IllegalArgumentException("OTP key must be non-null.");
        }
        if (value == null) {
            throw new IllegalArgumentException("OTP value must be non-null.");
        }
        if (!value.matches(OTP_PATTERN)) {
            throw new IllegalArgumentException("OTP value must be alphanumeric string.");
        }
        this.value = value;
        this.key = key;
    }

    public OTP(int value, String key) {
        this(String.valueOf(value), key);
    }

    /**
     * Constructor for backward compatibility.
     * Uses the value as the key for simplicity.
     *
     * @param value The OTP value
     */
    public OTP(String value) {
        this(value, value);
    }

    /**
     * Constructor for backward compatibility.
     * Uses the string representation of the value as the key.
     * @param value The OTP numeric value
     */
    public OTP(int value) {
        this(String.valueOf(value), String.valueOf(value));
    }

    @Override
    public String toString() {
        // Avoid logging the actual OTP value and keys in production for security.
        return "OTP[value=******, key=" + (key != null ? key.substring(0, Math.min(3, key.length())) + "..." : "null") + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OTP otp = (OTP) o;
        return Objects.equals(value, otp.value) && Objects.equals(key, otp.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, key);
    }

    public int length() {
        return value.length();
    }
}
