package online.bankapp.authservice.listener;

import lombok.RequiredArgsConstructor;
import online.bankapp.authservice.event.UserVerificationReqEvent;
import online.bankapp.authservice.service.OTPService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserVerificationReqListener {

    private final OTPService otpService;

    @EventListener
    public void handleUserVerificationReq(UserVerificationReqEvent event) {
        var otp = otpService.generateAndPersitOTP(event.email().getValue());

    }
}
