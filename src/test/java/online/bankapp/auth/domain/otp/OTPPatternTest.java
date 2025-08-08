package online.bankapp.auth.domain.otp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("OTP Pattern Validation Tests")
class OTPPatternTest {

    private static final String OTP_PATTERN = "^[a-zA-Z0-9]+$";

    @Test
    @DisplayName("Should accept valid numeric OTP")
    void shouldAcceptValidNumericOTP() {
        String validOTP = "123456";
        assertTrue(validOTP.matches(OTP_PATTERN));
    }

    @Test
    @DisplayName("Should accept valid alphabetic OTP")
    void shouldAcceptValidAlphabeticOTP() {
        String validOTP = "AbCdEf";
        assertTrue(validOTP.matches(OTP_PATTERN));
    }

    @Test
    @DisplayName("Should accept valid alphanumeric OTP")
    void shouldAcceptValidAlphanumericOTP() {
        String validOTP = "Abc123";
        assertTrue(validOTP.matches(OTP_PATTERN));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "123456",           // All digits
            "ABCDEF",           // All uppercase letters
            "abcdef",           // All lowercase letters
            "Abc123",           // Mixed case with numbers
            "1",                // Single digit
            "A",                // Single uppercase letter
            "z",                // Single lowercase letter
            "1a2B3c4D5e6F",     // Long mixed alphanumeric
            "000000",           // All zeros
            "aaaaaa",           // Repeated lowercase
            "ZZZZZZ",           // Repeated uppercase
            "a1B2c3D4e5F6"      // Alternating pattern
    })
    @DisplayName("Should accept valid alphanumeric strings")
    void shouldAcceptValidAlphanumericStrings(String validOTP) {
        assertTrue(validOTP.matches(OTP_PATTERN),
                "Expected '" + validOTP + "' to match OTP pattern");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "123-456",          // Contains hyphen
            "123 456",          // Contains space
            "123.456",          // Contains dot
            "123_456",          // Contains underscore
            "ABC@123",          // Contains @ symbol
            "123#456",          // Contains hash
            "123$456",          // Contains dollar sign
            "123%456",          // Contains percent
            "123&456",          // Contains ampersand
            "123*456",          // Contains asterisk
            "123(456)",         // Contains parentheses
            "123+456",          // Contains plus
            "123=456",          // Contains equals
            "123[456]",         // Contains brackets
            "123{456}",         // Contains braces
            "123|456",          // Contains pipe
            "123\\456",         // Contains backslash
            "123/456",          // Contains forward slash
            "123:456",          // Contains colon
            "123;456",          // Contains semicolon
            "123,456",          // Contains comma
            "123<456>",         // Contains angle brackets
            "123?456",          // Contains question mark
            "123!456",          // Contains exclamation
            "123\"456\"",       // Contains quotes
            "123'456'",         // Contains single quotes
            "123`456`",         // Contains backticks
            "123~456",          // Contains tilde
            "123^456",          // Contains caret
            "Héllo123",         // Contains accented character
            "Ñice123",          // Contains special character
            "测试123",           // Contains non-Latin characters
            "🎉123",            // Contains emoji
            "\t123",            // Contains tab
            "\n123",            // Contains newline
            "\r123",            // Contains carriage return
            "123\u0000"         // Contains null character
    })
    @DisplayName("Should reject strings with special characters")
    void shouldRejectStringsWithSpecialCharacters(String invalidOTP) {
        assertFalse(invalidOTP.matches(OTP_PATTERN),
                "Expected '" + invalidOTP + "' to NOT match OTP pattern");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should handle null and empty strings")
    void shouldHandleNullAndEmptyStrings(String invalidOTP) {
        if (invalidOTP == null) {
            assertThrows(NullPointerException.class, () -> invalidOTP.matches(OTP_PATTERN));
        } else {
            assertFalse(invalidOTP.matches(OTP_PATTERN),
                    "Expected empty string to NOT match OTP pattern");
        }
    }

    @Test
    @DisplayName("Should reject string with only whitespace")
    void shouldRejectWhitespaceOnlyString() {
        String whitespaceOTP = "   ";
        assertFalse(whitespaceOTP.matches(OTP_PATTERN));
    }

    @Test
    @DisplayName("Should reject string with leading/trailing whitespace")
    void shouldRejectStringWithLeadingTrailingWhitespace() {
        String otpWithWhitespace = " 123456 ";
        assertFalse(otpWithWhitespace.matches(OTP_PATTERN));
    }

    @Test
    @DisplayName("Should work with very long valid strings")
    void shouldWorkWithVeryLongValidStrings() {
        StringBuilder longOTP = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            longOTP.append(i % 10);
        }
        String veryLongOTP = longOTP.toString();
        assertTrue(veryLongOTP.matches(OTP_PATTERN));
    }

    @Test
    @DisplayName("Should reject mixed valid and invalid characters")
    void shouldRejectMixedValidAndInvalidCharacters() {
        String mixedOTP = "Valid123Invalid!";
        assertFalse(mixedOTP.matches(OTP_PATTERN));
    }

    @Test
    @DisplayName("Pattern should be case sensitive for matching")
    void patternShouldBeCaseSensitive() {
        // Both should be valid as the pattern accepts both cases
        assertTrue("ABC123".matches(OTP_PATTERN));
        assertTrue("abc123".matches(OTP_PATTERN));
        assertTrue("AbC123".matches(OTP_PATTERN));
    }

    @Test
    @DisplayName("Should test edge cases with Unicode digits and letters")
    void shouldTestUnicodeEdgeCases() {
        // Unicode digits (not ASCII 0-9) should be rejected
        String unicodeDigits = "१२३४५६"; // Hindi digits
        assertFalse(unicodeDigits.matches(OTP_PATTERN));

        // Unicode letters (not ASCII a-z, A-Z) should be rejected
        String unicodeLetters = "αβγδεζ"; // Greek letters
        assertFalse(unicodeLetters.matches(OTP_PATTERN));
    }
}
