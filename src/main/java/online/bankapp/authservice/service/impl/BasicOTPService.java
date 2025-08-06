package online.bankapp.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import online.bankapp.authservice.model.vo.OTP;
import online.bankapp.authservice.repository.OTPRepository;
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

    private final OTPRepository otpRepository;

    public OTP generateAndPersitOTP(String key) {
        var otp = otpGenerator.generate(OTP_LEN);
        otpRepository.save(key, otp);
        return otp;
    }
}
