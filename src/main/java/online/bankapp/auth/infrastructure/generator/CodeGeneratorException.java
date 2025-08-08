package online.bankapp.auth.infrastructure.generator;

import online.bankapp.auth.infrastructure.exception.base.ErrorAuthServiceException;

public class CodeGeneratorException extends ErrorAuthServiceException {
    public CodeGeneratorException(String msg) {
        super(msg);
    }
}
