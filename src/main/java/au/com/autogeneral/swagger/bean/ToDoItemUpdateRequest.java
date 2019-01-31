package au.com.autogeneral.swagger.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;


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
