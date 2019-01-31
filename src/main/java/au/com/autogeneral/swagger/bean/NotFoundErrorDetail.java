package au.com.autogeneral.swagger.bean;

public class NotFoundErrorDetail {
    private String message;

    public NotFoundErrorDetail(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
