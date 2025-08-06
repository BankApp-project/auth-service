package online.bankapp.authservice.repository.impl;

import lombok.RequiredArgsConstructor;
import online.bankapp.authservice.model.vo.OTP;
import online.bankapp.authservice.repository.OTPRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
@RequiredArgsConstructor
public class RedisOTPRepository implements OTPRepository {

    @Value("${app.otp.ttl}")
    private int OTP_TTL;
    private static final String KEY_PREFIX = "otp:";
    private final StringRedisTemplate template;

    @Async
    @Override
    public void save(String key, OTP otp) {
        //if this key already exists in Redis, then will be overwritten.
        var fullKey = KEY_PREFIX + key;
        template.opsForValue().set(
                fullKey,
                otp.getValue(),
                Duration.ofMinutes(OTP_TTL));
    }

}
