package online.bankapp.auth.infrastructure.messaging.payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import online.bankapp.auth.domain.otp.model.OTP;

@Getter
public class EmailVerificationAttemptPayload implements Payload {

    private final String email;
    private final String otp;

    public EmailVerificationAttemptPayload(OTP otp) {
        this.email = otp.getKey();
        this.otp = otp.getValue();
    }

    @JsonCreator
    protected EmailVerificationAttemptPayload(
            @JsonProperty("email") String email,
            @JsonProperty("otp") String otp) {
        this.email = email;
        this.otp = otp;
    }
}