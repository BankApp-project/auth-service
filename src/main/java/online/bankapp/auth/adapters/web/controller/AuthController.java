package online.bankapp.auth.adapters.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.bankapp.auth.application.command.RequestEmailVerification;
import online.bankapp.auth.domain.user.vo.EmailAddress;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final ApplicationEventPublisher eventPublisher;

    @PostMapping("/init/{email}")
    public ResponseEntity<Void> init(@PathVariable EmailAddress email) {
        log.info("[AuthController][init] Request received from: {}", email);
        //TODO add here health checks
        eventPublisher.publishEvent(new RequestEmailVerification(email));
        return ResponseEntity.accepted().build();
    }
}
