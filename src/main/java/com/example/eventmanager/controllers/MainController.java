package com.example.eventmanager.controllers;

import com.example.eventmanager.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {
    private final UserService userService = new UserService();

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/validateUser")
    public String validateUser(WebRequest dataFromForm, HttpSession session) {
        int userId = userService.validateUser(dataFromForm);
        if (userId > 0) {
            session.setAttribute("userId", userId);
            return "redirect:/events";
        } else {
            // TODO create error page
         return "errorMsg";
        }

    }

    @GetMapping("/signUp")
    public String signUp() {
        return "signup";
    }

    @PostMapping("/createAccount")
    public String createAccount(WebRequest dataFromForm) {
        // TODO
        userService.createAccount(dataFromForm);
        return "redirect:/";
    }
}
