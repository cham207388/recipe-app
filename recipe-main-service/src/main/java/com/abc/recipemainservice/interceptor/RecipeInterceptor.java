package com.abc.recipemainservice.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class RecipeInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("ms ===> request URI is: {} and method is: {}", request.getRequestURI(), request.getMethod());
        log.info("ms ===> response status is: {}", response.getStatus());
        return true;
    }
}
