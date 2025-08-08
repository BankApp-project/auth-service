package online.bankapp.authservice.service.listener;

import lombok.RequiredArgsConstructor;
import online.bankapp.authservice.converter.JsonConverter;
import online.bankapp.authservice.dto.event.EmailVerificationRequest;
import online.bankapp.authservice.dto.payload.EmailVerificationAttemptPayload;
import online.bankapp.authservice.model.AggregateType;
import online.bankapp.authservice.model.OutboxEvent;
import online.bankapp.authservice.service.OTPService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailVerificationRequestListener {

    private final OTPService otpService;

    private final JsonConverter converter;

    @EventListener
    public void handleEmailVerificationReq(EmailVerificationRequest event) {
        var otp = otpService.generateOtp(event.email().getValue());
        otpService.persistOtp(otp);

        var payload = new EmailVerificationAttemptPayload(otp);
        String serializedPayload = converter.serialize(payload);
        var outboxEvent = new OutboxEvent(AggregateType.EMAIL_VERIFICATION_ATTEMPT, event.id(), "created", serializedPayload);

    }
}
