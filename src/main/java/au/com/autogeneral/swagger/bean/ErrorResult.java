package au.com.autogeneral.swagger.bean;

public class ErrorResult implements IValidationResult {
    private ErrorDetail[] details;
    private String name;

    public ErrorResult(ErrorDetail[] details, String name) {
        this.details = details;
        this.name = name;
    }

    public ErrorResult() {

    }

    public ErrorDetail[] getDetails() {
        return details;
    }

    public void setDetails(ErrorDetail[] details) {
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
