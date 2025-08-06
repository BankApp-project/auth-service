package online.bankapp.authservice.exception.handler;

import lombok.extern.slf4j.Slf4j;
import online.bankapp.authservice.exception.base.ErrorAuthServiceException;
import online.bankapp.authservice.exception.base.WarnAuthServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ErrorAuthServiceException.class)
    public void handleErrors(ErrorAuthServiceException e) {
        log.error(e.getMessage());
    }

    @ExceptionHandler(WarnAuthServiceException.class)
    public void handleWarnings(WarnAuthServiceException e) {
        log.warn(e.getMessage());
    }
}
