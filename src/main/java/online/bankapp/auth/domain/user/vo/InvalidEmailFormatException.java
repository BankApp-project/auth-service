package online.bankapp.auth.domain.user.vo;

import lombok.extern.slf4j.Slf4j;
import online.bankapp.auth.infrastructure.exception.base.WarnAuthServiceException;

@Slf4j
public class InvalidEmailFormatException extends WarnAuthServiceException {
    public InvalidEmailFormatException(String message) {
        super(message);
    }
}
