package au.com.autogeneral.swagger.bean;

public class ToDoItemUpdateRequest {
    private String text;
    private boolean isCompleted;
    public String getText() {
        return text;
    }

    public boolean isIsCompleted() {
        return isCompleted;
    }


    public void setText(String text) {
        this.text = text;
    }

    public void setIsCompleted(boolean completed) {
        isCompleted = completed;
    }


}
