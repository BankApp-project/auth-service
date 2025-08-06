package online.bankapp.authservice.service.impl;

import online.bankapp.authservice.exception.OTPGeneratorException;
import online.bankapp.authservice.model.vo.OTP;
import online.bankapp.authservice.service.OTPGenerator;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class NumberOTPGenerator implements OTPGenerator {

    private static final SecureRandom secureRandom = new SecureRandom();

    @Override
    public OTP generate(int len) {
        if (len <= 0) {
            throw new OTPGeneratorException("PIN length must be greater than 0");
        }

        if (len > 9) { // Prevent overflow
            throw new OTPGeneratorException("PIN length cannot exceed 9 digits");
        }

        return new OTP(getRandomNumber(len));
    }

    private int getRandomNumber(int len) {
        // Generate a random number within the range, ensuring minimum length
        int lowerBound = (int) Math.pow(10, len - 1);
        int upperBound = (int) Math.pow(10, len);
        return secureRandom.nextInt(upperBound - lowerBound) + lowerBound;
    }
}
