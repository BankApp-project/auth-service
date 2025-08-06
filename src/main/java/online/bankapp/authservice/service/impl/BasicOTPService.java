package online.bankapp.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import online.bankapp.authservice.model.vo.OTP;
import online.bankapp.authservice.service.OTPGenerator;
import online.bankapp.authservice.service.OTPService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BasicOTPService implements OTPService {

    @Value("${app.otp.length}")
    private int OTP_LEN;

    private final ApplicationEventPublisher eventPublisher;

    private final OTPGenerator otpGenerator;

    public OTP generateOTP() {
        return otpGenerator.generate(OTP_LEN);

    }
}
