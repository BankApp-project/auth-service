package online.bankapp.authservice.exception;

public class InvalidEmailFormatException extends RuntimeException {
    public InvalidEmailFormatException(String message) {
        //todo have to decide if we will follow ERROR_CODEs from monorepo
        super(message);
    }
}
