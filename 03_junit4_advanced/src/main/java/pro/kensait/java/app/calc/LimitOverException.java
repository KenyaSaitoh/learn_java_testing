package pro.kensait.java.app.calc;

public class LimitOverException extends Exception {

    public LimitOverException() {
        super();
    }

    public LimitOverException(String message, Throwable cause) {
        super(message, cause);
    }

    public LimitOverException(String message) {
        super(message);
    }

    public LimitOverException(Throwable cause) {
        super(cause);
    }
}