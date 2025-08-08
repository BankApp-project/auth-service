package online.bankapp.auth.domain.otp.service;

/**
 * Interface representing a general-purpose One-Time Password (OTP) generator.
 * Implementations of this interface are responsible for generating OTPs based
 * on a specified length.
 * <p>
 * Implementations may define their own constraints and formats for the OTP,
 * such as numeric-only or alphanumeric-based OTPs.
 * <p>
 * Common use cases for implementing this interface include secure authentication
 * systems, password recovery mechanisms, and time-sensitive transaction validation.
 */
public interface CodeGenerator {
    String generate(int len);
}
