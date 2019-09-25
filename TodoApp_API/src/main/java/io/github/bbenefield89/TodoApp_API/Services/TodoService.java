package io.github.bbenefield89.TodoApp_API.Services;

import io.github.bbenefield89.TodoApp_API.Entities.Todo;
import io.github.bbenefield89.TodoApp_API.Repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Lets Spring know to pick this up at runtime
 * You've probably noticed that so far we haven't really told Spring when to use any of our classes and that's
 * because of "Component Scanning". To learn more about the Component Scanning go to the following URL
 * https://www.baeldung.com/spring-component-scanning
 */
@Service
public class TodoService {

    TodoRepository todoRepository;

    /**
     * @Autowired annotation sets this constructor to be called when booting our application and will automagically
     * inject any dependencies that we specify in the arguments
     * This is also known as "Dependency Injection" and is one of the more attractive aspects of the Spring Framework
     */
    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // Returns a List of all of a users Todos
    public List<Todo> findAllByUserEmailAddress(String userEmailAddress) {
        return todoRepository.findAllByUserEmailAddress(userEmailAddress);
    }

    // Return a single Todo
    public Todo findByIdAndUserEmailAddress(Long todoId, String userEmailAddress) {
        return todoRepository.findByIdAndUserEmailAddress(todoId, userEmailAddress);
    }

    // Create/Update a new Todo and returns that Todo
    public Todo save(String userEmailAddress, Todo todo) {
        todo.setUserEmailAddress(userEmailAddress);
        return todoRepository.save(todo);
    }

    // Delete a Todo
    public void deleteByIdAndUserEmailAddress(Long todoId, String userEmailAddress) {
        todoRepository.deleteByIdAndUserEmailAddress(todoId, userEmailAddress);
    }

}
