package io.github.bbenefield89.TodoApp_API.Controllers;

import io.github.bbenefield89.TodoApp_API.Entities.Todo;
import io.github.bbenefield89.TodoApp_API.Services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // Returns a List of Todos
    @GetMapping("/{userEmailAddress}")
    public List<Todo> findAllByUserEmailAddress(@PathVariable String userEmailAddress) {
        return todoService.findAllByUserEmailAddress(userEmailAddress);
    }

    // Returns a single Todo
    @GetMapping("/{userEmailAddress}/{todoId}")
    public Todo findByIdAndUserEmailAddress(@PathVariable String userEmailAddress, @PathVariable Long todoId) {
        return todoService.findByIdAndUserEmailAddress(todoId, userEmailAddress);
    }

    // Creates a new Todo
    @PostMapping("/{userEmailAddress}")
    public Todo save(@PathVariable String userEmailAddress, @RequestBody Todo todo) {
        return todoService.save(userEmailAddress, todo);
    }

    // Deletes a single Todo
    @DeleteMapping("/{userEmailAddress}/{todoId}")
    public void deleteByIdAndUserEmailAddress(@PathVariable String userEmailAddress, @PathVariable Long todoId) {
        todoService.deleteByIdAndUserEmailAddress(todoId, userEmailAddress);
    }

}
