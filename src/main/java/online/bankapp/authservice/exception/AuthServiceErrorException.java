package online.bankapp.authservice.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthServiceErrorException extends RuntimeException {

    public AuthServiceErrorException(String msg) {
        log.error(msg);
    }

    public AuthServiceErrorException(String msg, Throwable e) {
        log.error(msg, e);
    }
}
