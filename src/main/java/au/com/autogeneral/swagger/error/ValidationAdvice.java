package au.com.autogeneral.swagger.error;

import au.com.autogeneral.swagger.bean.NotFoundError;
import au.com.autogeneral.swagger.bean.NotFoundErrorDetail;
import au.com.autogeneral.swagger.bean.ValidationErrorDetail;
import au.com.autogeneral.swagger.bean.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ValidationAdvice {

    @ResponseBody
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ValidationError validationExceptionHandler(ValidationException ex) {
        return new ValidationError(new ValidationErrorDetail[] {
                new ValidationErrorDetail("params", "text", "Must be between 1 and 50 chars long", ex.getText())
        }, "ValidationError");
    }

    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    NotFoundError notFoundExceptionHandler(NotFoundException ex) {
        return new NotFoundError(new NotFoundErrorDetail[] {
                new NotFoundErrorDetail("Item with " + ex.getId() + " not found")
        }, "NotFoundError");
    }
}
