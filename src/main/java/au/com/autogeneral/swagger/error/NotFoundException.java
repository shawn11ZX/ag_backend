package au.com.autogeneral.swagger.error;

/**
 * Exception while item not found
 *
 * @author Shawn Chang
 */
public class NotFoundException extends RuntimeException{
    final private String id;

    public NotFoundException(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
