package online.bankapp.authservice.exception;

import lombok.extern.slf4j.Slf4j;
import online.bankapp.authservice.exception.base.ErrorAuthServiceException;

@Slf4j
public class ConverterException extends ErrorAuthServiceException {
    public ConverterException(String msg, Exception e) {
        super(msg, e);
    }
}
