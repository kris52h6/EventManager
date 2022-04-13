package com.example.eventmanager.services;

import com.example.eventmanager.repositories.UserRepo;
import org.springframework.web.context.request.WebRequest;

public class UserService {
    private UserRepo userRepo;

    public UserService() {
        this.userRepo = new UserRepo();
    }

    public int validateUser(WebRequest dataFromForm) {
        String username = dataFromForm.getParameter("username");
        String password = dataFromForm.getParameter("password");
        return userRepo.validateUser(username, password);
    }
}
