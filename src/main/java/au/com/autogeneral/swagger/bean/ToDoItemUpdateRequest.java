package au.com.autogeneral.swagger.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * implementation of the 'ToDoItemUpdateRequest ' in swagger
 *
 *  @author Shawn Chang
 */
public class ToDoItemUpdateRequest {

    @JsonProperty(required = false)
    private String text;
    private boolean hasText;

    @JsonProperty(required = false)
    private boolean isCompleted;
    private boolean hasIsCompleted;
    public String getText() {
        return text;
    }

    public boolean isIsCompleted() {
        return isCompleted;
    }


    public void setText(String text) {
        this.text = text;
        hasText = true;
    }

    public void setIsCompleted(boolean completed) {
        isCompleted = completed;
        hasIsCompleted = true;
    }

    public boolean hasText() {
        return hasText;
    }

    public boolean hasIsCompleted() {
        return hasIsCompleted;
    }
}
