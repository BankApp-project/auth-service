package online.bankapp.authservice.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConverterException extends RuntimeException {
    public ConverterException(String msg, Exception e) {
        log.error(msg, e);
    }
}
