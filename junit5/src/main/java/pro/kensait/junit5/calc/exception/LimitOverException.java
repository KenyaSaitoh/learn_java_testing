package pro.kensait.junit5.calc.exception;

/*
 * 極度オーバー時に発生する例外
 */
public class LimitOverException extends RuntimeException {

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