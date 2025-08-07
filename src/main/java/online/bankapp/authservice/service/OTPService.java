package online.bankapp.authservice.service;

import online.bankapp.authservice.model.vo.OTP;

/**
 * Service interface for managing One-Time Passwords (OTPs).
 * Provides functionality to generate and persist an OTP
 * associated with a specific key.<br>
 *
 * <br>Implementations of this interface should ensure the effective
 * generation, validation, and storage of secure OTPs for use
 * in authentication or verification processes.<br>
 *
 * <br>Method Details:
 * <br>- {@code generateAndPersitOTP}: Creates an OTP and stores it
 * against the provided key for later retrieval or validation.<br>
 */
public interface OTPService {

    OTP generateOtp(String key);

    void persistOtp(OTP otp);

}
