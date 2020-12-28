package ua.lpnu.lab4spring.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ServiceException extends RuntimeException {

    private HttpStatus code;
    private String message;

    public ServiceException(final HttpStatus code, final String message) {
        this.code = code;
        this.message = message;
    }

    public ServiceException(final String message, final Throwable cause, final HttpStatus code) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }
}
