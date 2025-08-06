package online.bankapp.authservice.exception.base;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ErrorAuthServiceException extends RuntimeException {

    public ErrorAuthServiceException(String msg) {
        super(msg);
    }

    public ErrorAuthServiceException(String msg, Throwable e) {
        super(msg, e);
    }
}
