package pro.kensait.spring.bookstore.rest;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException() {
        super();
    }

    public CustomerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerNotFoundException(String message) {
        super(message);
    }

    public CustomerNotFoundException(Throwable cause) {
        super(cause);
    }
}