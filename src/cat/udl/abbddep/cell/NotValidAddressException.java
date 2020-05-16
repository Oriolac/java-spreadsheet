package cat.udl.abbddep.cell;

public class NotValidAddressException extends Exception {
    public NotValidAddressException() {
    }

    public NotValidAddressException(String message) {
        super(message);
    }

    public NotValidAddressException(Throwable cause) {
        super(cause);
    }
}
