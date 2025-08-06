package online.bankapp.authservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.bankapp.authservice.model.vo.EmailAddress;
import online.bankapp.authservice.service.VerificationService;
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

    private final VerificationService verificationService;

    @PostMapping("/init/{email}")
    public ResponseEntity<Void> init(@PathVariable EmailAddress email) {
        log.info("[AuthController][init] Request received from: {}", email);

        verificationService.init(email);
        //TODO add here health checks
        return ResponseEntity.accepted().build();
    }

}
