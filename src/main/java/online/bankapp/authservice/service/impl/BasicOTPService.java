package online.bankapp.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import online.bankapp.authservice.service.OTPGenerator;
import online.bankapp.authservice.service.OTPService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BasicOTPService implements OTPService {

    @Value("${app.otp.length}")
    private int OTP_LEN;

    private final OTPGenerator otpGenerator;

    public String generateOTP() {
        return otpGenerator.generateDigitPIN(OTP_LEN);
    }
}
