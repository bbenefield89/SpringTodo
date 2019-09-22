package io.github.bbenefield89.SpringTodo_Auth.Models;

import lombok.Data;

@Data
public class User {

    private String sub;
    private String given_name;
    private String family_name;
    private String nickname;
    private String name;
    private String picture;
    private String locale;
    private String updated_at;
    private String email;
    private boolean email_verified;

}
