package online.bankapp.auth.domain.otp;

import online.bankapp.auth.domain.otp.model.OTP;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OTPTest {

    @Test
    void givenNumericOTP_whenLengthCalled_thenCorrectLengthReturned() {
        // Arrange
        OTP otp = new OTP(123456, "user123");

        // Act
        int length = otp.length();

        // Assert
        assertEquals(6, length);
    }

    @Test
    void givenAlphanumericOTP_whenLengthCalled_thenCorrectLengthReturned() {
        // Arrange
        OTP otp = new OTP("AB123", "user456");

        // Act
        int length = otp.length();

        // Assert
        assertEquals(5, length);
    }

    @Test
    void givenSingleCharacterOTP_whenLengthCalled_thenCorrectLengthReturned() {
        // Arrange
        OTP otp = new OTP("X", "user789");

        // Act
        int length = otp.length();

        // Assert
        assertEquals(1, length);
    }

    @Test
    void givenEmptyStringOTP_thenShouldThrowException() {
        // Assert
        assertThrows(IllegalArgumentException.class, () -> new OTP("", "user101"));
    }

    @Test
    void givenOTP_whenToStringCalled_thenMaskedValueReturned() {
        // Arrange
        OTP otp = new OTP(123456, "user123");

        // Act
        String result = otp.toString();

        // Assert
        assertEquals("OTP[value=******, key=use...]", result);
    }

    @Test
    void givenOTPsWithSameValueAndKey_whenCompared_thenEquals() {
        // Arrange
        OTP otp1 = new OTP("12345", "user123");
        OTP otp2 = new OTP("12345", "user123");

        // Act & Assert
        assertEquals(otp1, otp2);
        assertEquals(otp1.hashCode(), otp2.hashCode());
    }

    @Test
    void givenOTPsWithSameValueButDifferentKey_whenCompared_thenNotEquals() {
        // Arrange
        OTP otp1 = new OTP("12345", "user123");
        OTP otp2 = new OTP("12345", "user456");

        // Act & Assert
        assertNotEquals(otp1, otp2);
        assertNotEquals(otp1.hashCode(), otp2.hashCode());
    }
}