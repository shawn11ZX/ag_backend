package au.com.autogeneral.swagger.error;

/**
 * Exception while text not valid
 *
 * @author Shawn Chang
 */
public class ValidationException extends RuntimeException {
    final private String text;

    public ValidationException(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
