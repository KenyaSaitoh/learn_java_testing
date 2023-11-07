package pro.kensait.spring.mvc.bookstore.service.login;

public class CustomerNotFoundException extends Exception {
    private LoginTO loginTO;

    public CustomerNotFoundException() {
        super();
    }

    public CustomerNotFoundException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
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
    
    public CustomerNotFoundException(LoginTO loginTO) {
        this.loginTO = loginTO;
    }

    public LoginTO getLoginTO() {
        return loginTO;
    }

    public void setLoginTO(LoginTO loginTO) {
        this.loginTO = loginTO;
    }
}
