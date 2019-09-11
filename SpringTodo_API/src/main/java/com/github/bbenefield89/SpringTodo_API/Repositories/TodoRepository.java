package com.github.bbenefield89.SpringTodo_API.Repositories;

import com.github.bbenefield89.SpringTodo_API.Entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
