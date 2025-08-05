package online.bankapp.authservice.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthServiceWarnException extends RuntimeException {

    public AuthServiceWarnException(String msg) {
        log.warn(msg);
    }

    public AuthServiceWarnException(String msg, Throwable e) {
        log.warn(msg, e);
    }
}
