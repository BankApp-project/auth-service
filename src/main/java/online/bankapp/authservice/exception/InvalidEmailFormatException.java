package online.bankapp.authservice.exception;

import lombok.extern.slf4j.Slf4j;
import online.bankapp.authservice.exception.base.WarnAuthServiceException;

@Slf4j
public class InvalidEmailFormatException extends WarnAuthServiceException {
    public InvalidEmailFormatException(String message) {
        super(message);
    }
}
