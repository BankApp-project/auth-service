package online.bankapp.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import online.bankapp.authservice.dto.event.EmailVerificationRequest;
import online.bankapp.authservice.model.vo.EmailAddress;
import online.bankapp.authservice.service.VerificationService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasicVerificationService implements VerificationService {

    private final ApplicationEventPublisher eventPublisher;

    @Async
    @Override
    public void init(EmailAddress email) {
        eventPublisher.publishEvent(new EmailVerificationRequest(email));
    }
}
