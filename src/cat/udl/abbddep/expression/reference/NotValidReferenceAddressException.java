package cat.udl.abbddep.expression.reference;

public class NotValidReferenceAddressException extends Exception {
    public NotValidReferenceAddressException() {
    }

    public NotValidReferenceAddressException(String message) {
        super(message);
    }

    public NotValidReferenceAddressException(Throwable cause) {
        super(cause);
    }
}
