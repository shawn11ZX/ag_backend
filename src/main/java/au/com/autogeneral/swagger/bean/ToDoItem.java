package au.com.autogeneral.swagger.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class ToDoItem {
    private @Id @GeneratedValue Integer id;
    private String text;
    private boolean isCompleted;
    private Date createAt;

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public boolean isIsCompleted() {
        return isCompleted;
    }

    public Date getCreateAt() {
        return createAt;
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

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
