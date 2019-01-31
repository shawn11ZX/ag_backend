package au.com.autogeneral.swagger.error;

public class NotFoundException extends RuntimeException{
    private Integer id;

    public NotFoundException(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
