package pro.kensait.spring.calc.service.exception;

public class ZeroDivideException extends Exception {

    public ZeroDivideException() {
        super();
    }

    public ZeroDivideException(String message, Throwable cause) {
        super(message, cause);
    }

    public ZeroDivideException(String message) {
        super(message);
    }

    public ZeroDivideException(Throwable cause) {
        super(cause);
    }
}