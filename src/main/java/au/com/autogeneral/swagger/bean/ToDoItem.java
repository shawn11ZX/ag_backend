package au.com.autogeneral.swagger.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * implementation of the 'ToDoItem' in swagger
 *
 *  @author Shawn Chang
 */
@Entity
public class ToDoItem {
    private @Id @GeneratedValue Integer id;
    private String text;
    private boolean isCompleted;
    private Date createdAt;

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public boolean isIsCompleted() {
        return isCompleted;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIsCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
