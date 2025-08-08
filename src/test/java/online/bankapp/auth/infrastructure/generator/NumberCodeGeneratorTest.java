package online.bankapp.auth.infrastructure.generator;

import online.bankapp.auth.domain.otp.service.CodeGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberCodeGeneratorTest {

    private final CodeGenerator codeGenerator = new NumberCodeGenerator();

    @Test
    void testGenerateValidLength() {
        // Test valid OTP lengths from 1 to 9
        for (int len = 1; len <= 9; len++) {
            String otp = codeGenerator.generate(len);
            assertEquals(len, otp.length(), "Generated OTP length should match the requested length");
            assertTrue(otp.matches("\\d+"), "Generated OTP should only contain digits");
        }
    }

    @Test
    void testGenerateZeroLength() {
        // Test OTP generation when length is 0
        CodeGeneratorException exception = assertThrows(CodeGeneratorException.class, () -> codeGenerator.generate(0));
        assertEquals("PIN length must be greater than 0", exception.getMessage());
    }

    @Test
    void testGenerateNegativeLength() {
        // Test OTP generation when length is negative
        CodeGeneratorException exception = assertThrows(CodeGeneratorException.class, () -> codeGenerator.generate(-1));
        assertEquals("PIN length must be greater than 0", exception.getMessage());
    }

    @Test
    void testGenerateExceedingMaxLength() {
        // Test OTP generation when length exceeds the allowed maximum of 9
        CodeGeneratorException exception = assertThrows(CodeGeneratorException.class, () -> codeGenerator.generate(10));
        assertEquals("PIN length cannot exceed 9 digits", exception.getMessage());
    }
}