package online.bankapp.authservice.controller;

import lombok.extern.slf4j.Slf4j;
import online.bankapp.authservice.model.EmailAddress;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {


    @PostMapping("/start/{email}")
    public ResponseEntity<Void> start(@PathVariable EmailAddress email) {
        log.info("[AuthController][start] Request received from: {}", email);

        return ResponseEntity.accepted().build();
    }

}
