package online.bankapp.auth.domain.otp.service;

import lombok.RequiredArgsConstructor;
import online.bankapp.auth.domain.otp.model.OTP;
import online.bankapp.auth.domain.otp.repository.OTPRepository;
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
