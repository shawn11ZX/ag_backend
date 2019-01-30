package au.com.autogeneral.swagger;

public class ToDoItemValidationError implements IValidationResult {
    private ToDoItemValidationErrorDetail[] details;
    private String name;

    public ToDoItemValidationError(ToDoItemValidationErrorDetail[] details, String name) {
        this.details = details;
        this.name = name;
    }

    public ToDoItemValidationError() {

    }

    public ToDoItemValidationErrorDetail[] getDetails() {
        return details;
    }

    public void setDetails(ToDoItemValidationErrorDetail[] details) {
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
