package io.github.bbenefield89.TodoApp_API.Repositories;

import io.github.bbenefield89.TodoApp_API.Entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Sets this interface up to be found by Spring
 * Later on we'll be taking advantage of the @Autowired annotation where this interface will then become a
 * concrete class
 */
@Repository
/**
 * Our repository interface needs to extend the JpaRepository interface and pass along two arguments
 * 1. The Entity class that this repository is responsible for
 * 2. The id data type we chose for the Entity this Repository is responsble for
 * In this example, we've chosen to create our id as a Long data type
 */
public interface TodoRepository extends JpaRepository<Todo, Long> {

    /**
     * This is a custom method we'll be using to get a list of todos depending on the users email address
     * JPA supports a type of DSL where we can create methods that relate to an Entity by using keywords
     * 1. "findAll": returns a List of Todo
     * 2. "By": This signifies that we are going to be giving something specific to look for in the DB
     * 3. "UserEmailAddress": Find a Todo that contains the correct "userEmailAddress" in the DB
     */
    public List<Todo> findAllByUserEmailAddress(String userEmailAddress);

    /**
     * Another custom method. This method will take the ID of a Todo and the users email address to return a
     * single Todo
     */
    public Todo findByIdAndUserEmailAddress(Long todoId, String userEmailAddress);

    /**
     * This custom method will delete a single Todo depending on the ID and the userEmailAddress
     */
    public void deleteByIdAndUserEmailAddress(Long todoId, String userEmailAddress);

}
