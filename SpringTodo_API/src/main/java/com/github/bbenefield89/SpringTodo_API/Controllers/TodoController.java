package com.github.bbenefield89.SpringTodo_API.Controllers;

import com.github.bbenefield89.SpringTodo_API.Entities.Todo;
import com.github.bbenefield89.SpringTodo_API.Services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:8081"})
@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> findAll() {
        System.out.println("\n\n\n===== findAll =====\n\n\n");
        return todoService.findAll();
    }

    @GetMapping("/{todoId}")
    public Todo findById(@PathVariable Long todoId) {
        return todoService.findById(todoId);
    }

    @PostMapping
    public Todo save(@RequestBody Todo todo) {
        return todoService.save(todo);
    }

    @DeleteMapping("/{todoId}")
    public void deleteById(@PathVariable Long todoId) {
        todoService.deleteById(todoId);
    }

}
