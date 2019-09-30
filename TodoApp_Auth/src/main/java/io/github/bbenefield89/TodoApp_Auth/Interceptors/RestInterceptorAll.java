package io.github.bbenefield89.TodoApp_Auth.Interceptors;

import io.github.bbenefield89.TodoApp_Auth.Models.User;
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
        /**
         * Wrap the logic of this method in a try/catch
         * If this method fails then we know that something is wrong with the "access_token"
         */
        try {
            HttpHeaders headers = setAuthorizationHeader(req);
            HttpEntity<String> entity = new HttpEntity<>("headers", headers);
            User user = getUserInfoFromAuth0(entity);
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
    private User getUserInfoFromAuth0(HttpEntity<String> entity) {
        RestTemplate httpRequest = new RestTemplate();
        return httpRequest.exchange(
                "https://bbenefield.auth0.com/userinfo",
                HttpMethod.GET,
                entity,
                User.class
        ).getBody();
    }

}
