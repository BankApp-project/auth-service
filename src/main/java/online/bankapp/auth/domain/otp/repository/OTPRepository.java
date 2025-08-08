package online.bankapp.auth.domain.otp.repository;

import online.bankapp.auth.domain.otp.model.OTP;

/**
 * Interface representing a repository for storing and managing One-Time Passwords (OTPs).
 * Provides a method to persist OTPs associated with a specific key.
 */
public interface OTPRepository {
    void save(OTP otp);
}
