package au.com.autogeneral.swagger.error;

public class ValidationException extends RuntimeException {
    private String text;

    public ValidationException(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
