package io.github.bbenefield89.SpringTodo_Auth.Controllers;

import io.github.bbenefield89.SpringTodo_Auth.Models.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/validate")
public class ValidateController {

    @GetMapping
    public Object validate(@RequestHeader("Authorization") String authorizationHeader) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader);
        HttpEntity<String> entity = new HttpEntity<>("headers", headers);
        User user = restTemplate.exchange(
                "https://bbenefield.auth0.com/userinfo",
                HttpMethod.POST,
                entity,
                User.class).getBody();
        return user;
    }

}
