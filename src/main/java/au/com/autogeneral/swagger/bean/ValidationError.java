package au.com.autogeneral.swagger.bean;

/**
 * implementation of the 'ToDoItemValidationError' in swagger
 *
 *  @author Shawn Chang
 */
public class ValidationError {
    private ValidationErrorDetail[] details;
    private String name;

    public ValidationError(ValidationErrorDetail[] details, String name) {
        this.details = details;
        this.name = name;
    }

    public ValidationError() {

    }

    public ValidationErrorDetail[] getDetails() {
        return details;
    }

    public void setDetails(ValidationErrorDetail[] details) {
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
