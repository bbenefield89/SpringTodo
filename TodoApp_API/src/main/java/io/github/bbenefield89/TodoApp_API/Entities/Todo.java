package io.github.bbenefield89.TodoApp_API.Entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * This annotation comes from "Lombok" and allows us to forget about writing
 * a lot of boilerplate code like "Constructors/Getters/Setter"
 */
@Data
// Creates this class as a Bean to be picked up by Spring
@Entity
public class Todo {

    // Lets JPA know this is the unique identifier for our DB
    @Id
    // Sets the value that should be automatically generated for our ID in the DB
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    // We'll use the users' email address to find a user's todos
    private String userEmailAddress;

    /**
     * Notice we don't have to write anything else
     * Lombok will take care of this for us
     */

}