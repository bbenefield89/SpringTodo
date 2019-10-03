package io.github.bbenefield89.TodoApp_API.Controllers;

import io.github.bbenefield89.TodoApp_API.Entities.Todo;
import io.github.bbenefield89.TodoApp_API.Models.User;
import io.github.bbenefield89.TodoApp_API.Services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    public List<Todo> findAllByUserEmailAddress(
            @SessionAttribute User user,
            @PathVariable String userEmailAddress,
            HttpServletResponse res) {

        if (user.getEmail().equals(userEmailAddress)) {
            return todoService.findAllByUserEmailAddress(userEmailAddress);
        } else {
            todoService.unAuthorizedAccess(res);
            return null;
        }
    }

    // Returns a single Todo
    @GetMapping("/{userEmailAddress}/{todoId}")
    public Todo findByIdAndUserEmailAddress(
            @SessionAttribute User user,
            @PathVariable String userEmailAddress,
            @PathVariable Long todoId,
            HttpServletResponse res) {

        if (user.getEmail().equals(userEmailAddress)) {
            return todoService.findByIdAndUserEmailAddress(todoId, userEmailAddress);
        } else {
            todoService.unAuthorizedAccess(res);
            return null;
        }
    }

    // Creates a new Todo
    @PostMapping("/{userEmailAddress}")
    public Todo save(
            @SessionAttribute User user,
            @PathVariable String userEmailAddress,
            @RequestBody Todo todo,
            HttpServletResponse res) {

        if (user.getEmail().equals(userEmailAddress)) {
            return todoService.save(userEmailAddress, todo);
        } else {
            todoService.unAuthorizedAccess(res);
            return null;
        }
    }

    // Deletes a single Todo
    @DeleteMapping("/{userEmailAddress}/{todoId}")
    public void deleteByIdAndUserEmailAddress(
            @SessionAttribute User user,
            @PathVariable String userEmailAddress,
            @PathVariable Long todoId,
            HttpServletResponse res) {

        if (user.getEmail().equals(userEmailAddress)) {
            todoService.deleteByIdAndUserEmailAddress(todoId, userEmailAddress);
        } else {
            todoService.unAuthorizedAccess(res);
        }
    }

}
