package io.github.bbenefield89.TodoApp_API.Interceptors;

import io.github.bbenefield89.TodoApp_API.Models.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RestInterceptorAll extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        try {
            HttpHeaders headers = setAuthorizationHeader(req);
            HttpEntity<String> entity = new HttpEntity<>("headers", headers);
            User user = getUserInfoFromAuthService(entity);
            req.getSession().setAttribute("user", user);
            return super.preHandle(req, res, handler);
        } catch (Exception e) {
            // Users "access_token" is wrong so we should notify them that they're unauthorized (401)
            res.setStatus(401, "401 Unauthorized");
            // Return "false" so the "ValidateController" method isn't called
            return false;
        }
    }

    // Sets the "Authorization" header value (Authorization: Bearer ${access_token})
    private HttpHeaders setAuthorizationHeader(HttpServletRequest req) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", req.getHeader("Authorization"));
        return headers;
    }

    // Sends a GET request grab the users info
    private User getUserInfoFromAuthService(HttpEntity<String> entity) {
        RestTemplate httpRequest = new RestTemplate();
        return httpRequest.exchange(
                "http://localhost:8081/api/validate",
                HttpMethod.GET,
                entity,
                User.class
        ).getBody();
    }

}

