package online.bankapp.authservice.service;

import online.bankapp.authservice.model.vo.EmailAddress;

public interface VerificationService {
    void init(EmailAddress email);
}
