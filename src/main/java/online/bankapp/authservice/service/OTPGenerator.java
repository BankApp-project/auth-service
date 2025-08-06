package online.bankapp.authservice.service;

import online.bankapp.authservice.model.vo.OTP;

public interface OTPGenerator {
    OTP generate(int len);
}
