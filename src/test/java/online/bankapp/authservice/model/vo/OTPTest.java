package online.bankapp.authservice.model.vo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OTPTest {

    @Test
    void givenNumericOTP_whenLengthCalled_thenCorrectLengthReturned() {
        // Arrange
        OTP otp = new OTP(123456);

        // Act
        int length = otp.length();

        // Assert
        assertEquals(6, length);
    }

    @Test
    void givenAlphanumericOTP_whenLengthCalled_thenCorrectLengthReturned() {
        // Arrange
        OTP otp = new OTP("AB123");

        // Act
        int length = otp.length();

        // Assert
        assertEquals(5, length);
    }

    @Test
    void givenSingleCharacterOTP_whenLengthCalled_thenCorrectLengthReturned() {
        // Arrange
        OTP otp = new OTP("X");

        // Act
        int length = otp.length();

        // Assert
        assertEquals(1, length);
    }

    @Test
    void givenEmptyStringOTP_whenLengthCalled_thenLengthIsZero() {
        // Arrange
        OTP otp = new OTP("");

        // Act
        int length = otp.length();

        // Assert
        assertEquals(0, length);
    }

    @Test
    void givenOTP_whenToStringCalled_thenMaskedValueReturned() {
        // Arrange
        OTP otp = new OTP(123456);

        // Act
        String result = otp.toString();

        // Assert
        assertEquals("OTP[value=******]", result);
    }
}