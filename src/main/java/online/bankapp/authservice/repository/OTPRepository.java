package online.bankapp.authservice.repository;

import online.bankapp.authservice.model.vo.OTP;

public interface OTPRepository {

    public void save(String key, OTP otp);

}
