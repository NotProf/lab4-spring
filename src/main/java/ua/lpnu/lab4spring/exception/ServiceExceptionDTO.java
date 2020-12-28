package ua.lpnu.lab4spring.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ServiceExceptionDTO {
    private int code;
    private String message;

    public ServiceExceptionDTO() {
    }

    public ServiceExceptionDTO(final ServiceException ex) {
        this.code = ex.getCode().value();
        this.message = ex.getMessage();
    }


    public ServiceExceptionDTO(final HttpStatus code, final String message) {
        this.code = code.value();
        this.message = message;
    }
}
