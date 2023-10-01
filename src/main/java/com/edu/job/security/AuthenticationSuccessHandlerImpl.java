package com.edu.job.security;

import com.edu.job.model.User;
import com.edu.job.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.io.IOException;
import java.net.URL;
import java.security.Principal;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    @Autowired
    HttpSession session; //autowiring session

    @Autowired
    UserRepository repository; //autowire the user repo
    @Override
    public void onAuthenticationSuccess(

            HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String userName =  authentication.getName();

        User user = repository.getUserByEmail(userName);
        session.setAttribute("user", user);
    }
}
