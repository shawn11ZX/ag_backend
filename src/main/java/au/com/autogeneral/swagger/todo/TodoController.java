package au.com.autogeneral.swagger.todo;

import au.com.autogeneral.swagger.bean.ToDoItemAddRequest;
import au.com.autogeneral.swagger.bean.ToDoItemUpdateRequest;
import au.com.autogeneral.swagger.error.NotFoundException;
import au.com.autogeneral.swagger.error.ValidationException;
import au.com.autogeneral.swagger.bean.ToDoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class TodoController {
    @Autowired
    private TodoRepository repository;

    boolean isValid(String text) {
        return !StringUtils.isEmpty(text) && text.length() <= 50;
    }
    @PostMapping(value = "/todo/")
    public ToDoItem postTodo(@RequestBody ToDoItemAddRequest addRequest) {
        if (addRequest == null || !isValid(addRequest.getText())) {
            throw new ValidationException(addRequest.getText());
        }
        ToDoItem todo = new ToDoItem();
        todo.setText(addRequest.getText());
        todo.setIsCompleted(false);
        todo.setCreateAt(new Date());
        repository.save(todo);
        return todo;
    }

    @GetMapping(value = "/todo/{id}")
    public ToDoItem getTodo(@PathVariable final Integer id) {
        return repository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    @PatchMapping(value = "/todo/{id}")
    public ToDoItem getTodo(@PathVariable final Integer id, @RequestBody ToDoItemUpdateRequest updateRequest) {
        if (updateRequest == null || !isValid(updateRequest.getText())) {
            throw new ValidationException(updateRequest.getText());
        }
        ToDoItem rt = repository.findById(id).orElseThrow(()-> new NotFoundException(id));
        rt.setIsCompleted(updateRequest.isIsCompleted());
        rt.setText(updateRequest.getText());
        repository.save(rt);
        return rt;
    }



}
