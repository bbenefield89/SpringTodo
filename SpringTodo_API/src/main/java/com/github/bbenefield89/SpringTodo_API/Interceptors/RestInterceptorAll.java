package com.github.bbenefield89.SpringTodo_API.Interceptors;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RestInterceptorAll extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        System.out.println("\n\n\n===== preHandle =====\n\n\n");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", req.getHeader("Authorization"));

        HttpEntity<String> entity = new HttpEntity<>("headers", headers);

        RestTemplate httpRequest = new RestTemplate();
        Object user = httpRequest.exchange(
                "http://localhost:8081/api/validate",
                HttpMethod.GET,
                entity,
                Object.class).getBody();

        System.out.println("\n\n\n===== " + user + "=====\n\n\n");

        return super.preHandle(req, res, handler);
    }

    @Override
    public void postHandle(HttpServletRequest req,
                           HttpServletResponse res,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {

        System.out.println("\n\n\n===== postHandle ===== \n\n\n");
    }

}
