package online.bankapp.authservice.exception.base;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WarnAuthServiceException extends RuntimeException {

    public WarnAuthServiceException(String msg) {
        log.warn(msg);
    }

    public WarnAuthServiceException(String msg, Throwable e) {
        log.warn(msg, e);
    }
}
