package pro.kensait.spring.bookstore.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import pro.kensait.spring.bookstore.service.customer.CustomerExistsException;
import pro.kensait.spring.bookstore.service.customer.CustomerNotFoundException;

@RestControllerAdvice
public class CustomerExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFound(CustomerNotFoundException cnfx) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("customer.not-found", cnfx.getMessage()));
    }

    @ExceptionHandler(CustomerExistsException.class)
    public ResponseEntity<?> handleEntityExists(CustomerExistsException cee) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse("customer.exists", cee.getMessage()));
    }
}
