package com.github.bbenefield89.SpringTodo_API.ControllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bbenefield89.SpringTodo_API.Controllers.TodoController;
import com.github.bbenefield89.SpringTodo_API.Entities.Todo;
import com.github.bbenefield89.SpringTodo_API.Services.TodoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(TodoController.class)
public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

    private List<Todo> todos;

    public TodoControllerTest() {
        Todo firstTodo = new Todo();
        firstTodo.setId(1L);
        firstTodo.setTitle("My First Todo");

        Todo secondTodo = new Todo();
        secondTodo.setId(2L);
        secondTodo.setTitle("My Second Todo");

        todos = new ArrayList<>();
        todos.add(firstTodo);
        todos.add(secondTodo);
    }

    @Test
    public void findAllTodos() throws Exception {
        when(todoService.findAll()).thenReturn(todos);
        mockMvc.perform(get("/api/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))

                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("My First Todo")))

                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].title", is("My Second Todo")));
    }

    @Test
    public void findTodoById() throws Exception {
        when(todoService.findById(1L)).thenReturn(todos.get(0));
        mockMvc.perform(get("/api/todos/1"))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("My First Todo")));
    }

    @Test
    public void saveTodo() throws Exception {
        Todo thirdTodo = new Todo();
        thirdTodo.setId(3L);
        thirdTodo.setTitle("My Third Todo");
        ObjectMapper mapper = new ObjectMapper();

        String thirdTodoJSON = mapper.writeValueAsString(thirdTodo);

        when(todoService.save(thirdTodo)).thenReturn(thirdTodo);
        mockMvc.perform(post("/api/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(thirdTodoJSON))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.title", is("My Third Todo")));
    }

    @Test
    public void deleteTodo() throws Exception {
        mockMvc.perform(delete("/api/todos/3"))
                .andExpect(status().isOk());
    }

}
