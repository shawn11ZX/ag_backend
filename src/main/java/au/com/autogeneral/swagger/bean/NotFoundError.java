package au.com.autogeneral.swagger.bean;

/**
 * implementation of the 'ToDoItemNotFoundError' in swagger
 *
 *  @author Shawn Chang
 */
public class NotFoundError {
    private NotFoundErrorDetail details[];
    private String name;

    public NotFoundError(NotFoundErrorDetail [] details, String name) {
        this.details = details;
        this.name = name;
    }

    public NotFoundError() {

    }

    public NotFoundErrorDetail[] getDetails() {
        return details;
    }

    public void setDetails(NotFoundErrorDetail[] details) {
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
