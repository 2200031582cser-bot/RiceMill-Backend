package com.ricemill.backend.controller;

import com.ricemill.backend.entity.User;
import com.ricemill.backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // SIGNUP

    @PostMapping("/signup")
    public User signup(
            @RequestBody User user
    ) {

        return userRepository.save(user);
    }

    // LOGIN

    @PostMapping("/login")
    public User login(
            @RequestBody User loginUser
    ) {

        User user =
                userRepository.findByUsername(
                        loginUser.getUsername()
                );

        if (
                user != null &&
                        user.getPassword().equals(
                                loginUser.getPassword()
                        )
        ) {

            return user;
        }

        return null;
    }
}