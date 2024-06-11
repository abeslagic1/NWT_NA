//package com.etf.configs;
//
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//public class LoggerInterceptor extends HandlerInterceptorAdapter {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
//                             Object handler) {
//
//        System.out.println(request.getRemoteAddr());
//        return true;
//    }
//}