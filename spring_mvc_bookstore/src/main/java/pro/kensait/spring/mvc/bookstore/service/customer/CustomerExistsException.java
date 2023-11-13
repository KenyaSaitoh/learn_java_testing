package pro.kensait.spring.mvc.bookstore.service.customer;

public class CustomerExistsException extends RuntimeException {
    public CustomerExistsException() {
        super();
    }

    public CustomerExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerExistsException(String message) {
        super(message);
    }

    public CustomerExistsException(Throwable cause) {
        super(cause);
    }
}