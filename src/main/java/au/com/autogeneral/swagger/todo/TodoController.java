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

/**
 * implementation for todo related rest API
 *
 * @author Shawn Chang
 */
@RestController
public class TodoController {

    private final TodoRepository repository;

    @Autowired
    public TodoController(TodoRepository repository) {
        this.repository = repository;
    }

    /**
     * test whether the text of todo item is valid
     * @param text
     * @return true if valid, false else
     */
    private boolean isValid(String text) {
        return !StringUtils.isEmpty(text) && text.length() <= 50;
    }


    /**
     * implementation for POST todo item
     * @param addRequest, item definition
     * @return
     */
    @PostMapping(value = "/todo")
    public ToDoItem postTodo(@RequestBody ToDoItemAddRequest addRequest) {
        if (addRequest == null || !isValid(addRequest.getText())) {
            throw new ValidationException(addRequest.getText());
        }
        ToDoItem todo = new ToDoItem();
        todo.setText(addRequest.getText());
        todo.setIsCompleted(false);
        todo.setCreatedAt(new Date());
        repository.save(todo);
        return todo;
    }

    /**
     * implementation for GET todo item
     * @param id item id
     * @return
     */
    @GetMapping(value = "/todo/{id}")
    public ToDoItem patchTodo(@PathVariable final String id) {
        return repository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }


    /**
     * implementation for PATCH todo item
     * @param id, item id
     * @param updateRequest, request body
     * @return
     */
    @PatchMapping(value = "/todo/{id}")
    public ToDoItem patchTodo(@PathVariable final String id, @RequestBody ToDoItemUpdateRequest updateRequest) {
        if (updateRequest == null || (updateRequest.hasText() && !isValid(updateRequest.getText()))) {
            throw new ValidationException(updateRequest.getText());
        }
        ToDoItem rt = repository.findById(id).orElseThrow(()-> new NotFoundException(id));
        if (updateRequest.hasIsCompleted())
            rt.setIsCompleted(updateRequest.isIsCompleted());
        if (updateRequest.hasText())
            rt.setText(updateRequest.getText());
        repository.save(rt);
        return rt;
    }



}
