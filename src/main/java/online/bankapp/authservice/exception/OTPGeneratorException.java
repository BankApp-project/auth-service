package online.bankapp.authservice.exception;

import online.bankapp.authservice.exception.base.ErrorAuthServiceException;

public class OTPGeneratorException extends ErrorAuthServiceException {
    public OTPGeneratorException(String msg) {
        super(msg);
    }
}
