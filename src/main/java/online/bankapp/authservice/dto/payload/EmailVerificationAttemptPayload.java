package online.bankapp.authservice.dto.payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import online.bankapp.authservice.model.vo.OTP;

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
