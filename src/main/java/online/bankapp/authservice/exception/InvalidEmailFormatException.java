package online.bankapp.authservice.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InvalidEmailFormatException extends AuthServiceWarnException {
    public InvalidEmailFormatException(String message) {
        super(message);
    }
}
