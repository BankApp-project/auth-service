package online.bankapp.authservice.repository;

import online.bankapp.authservice.model.vo.OTP;

/**
 * Interface representing a repository for storing and managing One-Time Passwords (OTPs).
 * Provides a method to persist OTPs associated with a specific key.
 */
public interface OTPRepository {

    void save(String key, OTP otp);

}
