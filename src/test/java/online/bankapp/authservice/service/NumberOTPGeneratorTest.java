package online.bankapp.authservice.service;

import online.bankapp.authservice.exception.OTPGeneratorException;
import online.bankapp.authservice.service.impl.NumberOTPGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberOTPGeneratorTest {

    private final OTPGenerator otpGenerator = new NumberOTPGenerator();

    /**
     * Test case for generating a valid PIN of length 6.
     */
    @Test
    void testGenerate_ValidLength() {
        int pinLength = 6;
        var pin = otpGenerator.generate(pinLength);
        assertNotNull(pin, "Generated PIN should not be null");
        assertEquals(pinLength, pin.length(), "Generated PIN length should match the requested length");
        assertTrue(pin.getValue().chars().allMatch(Character::isDigit), "Generated PIN should contain only numeric characters");
    }

    /**
     * Test case for generating a valid PIN of the maximum allowed length (9).
     */
    @Test
    void testGenerate_MaxLength() {
        int pinLength = 9;
        var pin = otpGenerator.generate(pinLength);
        assertNotNull(pin, "Generated PIN should not be null");
        assertEquals(pinLength, pin.length(), "Generated PIN length should match the requested length");
        assertTrue(pin.getValue().chars().allMatch(Character::isDigit), "Generated PIN should contain only numeric characters");
    }

    /**
     * Test case for throwing an exception when a length of 0 is passed.
     */
    @Test
    void testGenerate_LengthZero() {
        int pinLength = 0;
        Exception exception = assertThrows(OTPGeneratorException.class, () -> otpGenerator.generate(pinLength));
        assertEquals("PIN length must be greater than 0", exception.getMessage());
    }

    /**
     * Test case for throwing an exception when a negative length is passed.
     */
    @Test
    void testGenerate_NegativeLength() {
        int pinLength = -5;
        Exception exception = assertThrows(OTPGeneratorException.class, () -> otpGenerator.generate(pinLength));
        assertEquals("PIN length must be greater than 0", exception.getMessage());
    }

    /**
     * Test case for throwing an exception when the length exceeds the maximum limit.
     */
    @Test
    void testGenerate_ExceedsMaxLength() {
        int pinLength = 10;
        Exception exception = assertThrows(OTPGeneratorException.class, () -> otpGenerator.generate(pinLength));
        assertEquals("PIN length cannot exceed 9 digits", exception.getMessage());
    }
}