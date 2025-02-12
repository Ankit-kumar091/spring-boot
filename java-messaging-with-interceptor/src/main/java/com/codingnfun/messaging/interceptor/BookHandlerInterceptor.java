package com.codingnfun.messaging.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class BookHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    private JavaMailSender javaMailSender;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object handler) {
        if(request.getParameter("bookId") != null) {
            System.out.println("PreHandle() sending book access mail...");
            sendEmail(request.getParameter("bookId"), "Book accessed");
            System.out.println("Done");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) {
        if(request.getParameter("bookId") != null) {
            System.out.println("PostHandle() sending book access mail...");
            sendEmail(request.getParameter("bookId"), "Book access complete");
            System.out.println("Done");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse httpServletResponse, Object handler, Exception ex) {
        if(request.getParameter("bookId") != null) {
            System.out.println("PostHandle() sending book access mail...");
            sendEmail(request.getParameter("bookId"), "Request and response is completed");
            System.out.println("Done");
        }
    }

    private void sendEmail(String bookId, String msgtext) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("codingnfun2025@gmail.com");
        msg.setSubject("Book related activity for book: " + bookId);
        msg.setText(msgtext + ": " + dateFormat.format(new Date()));
        javaMailSender.send(msg);
    }

}
