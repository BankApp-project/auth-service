package online.bankapp.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import online.bankapp.authservice.model.vo.OTP;
import online.bankapp.authservice.repository.OTPRepository;
import online.bankapp.authservice.service.CodeGenerator;
import online.bankapp.authservice.service.OTPService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BasicOTPService implements OTPService {

    @Value("${app.otp.length}")
    private int OTP_LEN;

    private final CodeGenerator codeGenerator;

    private final OTPRepository otpRepository;

    public OTP generateOtp(String key) {
        var code = codeGenerator.generate(OTP_LEN);
        return new OTP(code, key);
    }

    public void persistOtp(OTP otp) {
        otpRepository.save(otp);
    }
}
