package online.bankapp.auth.application.handler;

import lombok.RequiredArgsConstructor;
import online.bankapp.auth.application.command.RequestEmailVerification;
import online.bankapp.auth.domain.otp.service.OTPService;
import online.bankapp.auth.infrastructure.converter.JsonConverter;
import online.bankapp.auth.infrastructure.messaging.outbox.AggregateType;
import online.bankapp.auth.infrastructure.messaging.outbox.OutboxEvent;
import online.bankapp.auth.infrastructure.messaging.payload.EmailVerificationAttemptPayload;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailVerificationRequestHandler {

    private final OTPService otpService;

    private final JsonConverter converter;

    @EventListener
    public void handleEmailVerificationReq(RequestEmailVerification event) {
        var otp = otpService.generateOtp(event.email().getValue());
        otpService.persistOtp(otp);

        var payload = new EmailVerificationAttemptPayload(otp);
        String serializedPayload = converter.serialize(payload);
        var outboxEvent = new OutboxEvent(AggregateType.EMAIL_VERIFICATION_ATTEMPT, event.id(), "created", serializedPayload);

    }
}
