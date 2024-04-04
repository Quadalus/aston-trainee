package hw4.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage entityNotFoundException(final EntityNotFoundException e) {
        return new ErrorMessage(e.getMessage(), e.getClass().getName());
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage nullPointerException(final NullPointerException e) {
        return new ErrorMessage(e.getMessage(),e.getClass().getName());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage exceptionHandle(final Exception e) {
        return new ErrorMessage(e.getMessage(),e.getClass().getName());
    }
}
