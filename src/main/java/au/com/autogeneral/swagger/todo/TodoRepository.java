package au.com.autogeneral.swagger.todo;

import au.com.autogeneral.swagger.bean.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<ToDoItem, Integer> {

}
