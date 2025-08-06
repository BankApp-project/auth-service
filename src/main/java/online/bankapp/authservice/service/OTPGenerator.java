package online.bankapp.authservice.service;

import online.bankapp.authservice.exception.OTPGeneratorException;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class OTPGenerator {

    private static final SecureRandom secureRandom = new SecureRandom();

    public String generateDigitPIN(int len) {
        if (len <= 0) {
            throw new OTPGeneratorException("PIN length must be greater than 0");
        }

        if (len > 9) { // Prevent overflow
            throw new OTPGeneratorException("PIN length cannot exceed 9 digits");
        }

        return String.valueOf(getRandomNumber(len));
    }

    private int getRandomNumber(int len) {
        // Generate a random number within the range, ensuring minimum length
        int lowerBound = (int) Math.pow(10, len - 1);
        int upperBound = (int) Math.pow(10, len);
        return secureRandom.nextInt(upperBound - lowerBound) + lowerBound;
    }
}
