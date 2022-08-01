package com.factoria.coders.controllers;

import com.factoria.coders.models.User;
import com.factoria.coders.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    IUserRepository userRepository;

    @GetMapping("/users")
    List<User> getAll() {
        return userRepository.findAll();
    }
}
