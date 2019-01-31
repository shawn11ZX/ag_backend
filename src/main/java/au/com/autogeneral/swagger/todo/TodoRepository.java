package au.com.autogeneral.swagger.todo;

import au.com.autogeneral.swagger.bean.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ToDoItem repository stub
 *
 * @author Shawn Chang
 */
public interface TodoRepository extends JpaRepository<ToDoItem, Integer> {

}
