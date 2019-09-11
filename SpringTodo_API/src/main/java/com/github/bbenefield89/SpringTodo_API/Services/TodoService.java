package com.github.bbenefield89.SpringTodo_API.Services;

import com.github.bbenefield89.SpringTodo_API.Entities.Todo;
import com.github.bbenefield89.SpringTodo_API.Repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public Todo findById(Long todoId) {
        return todoRepository.findById(todoId).orElse(null);
    }

    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    public void deleteById(Long todoId) {
        todoRepository.deleteById(todoId);
    }

}
