package cat.udl.abbddep.reference;

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
