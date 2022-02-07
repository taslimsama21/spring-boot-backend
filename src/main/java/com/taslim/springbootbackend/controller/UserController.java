package com.taslim.springbootbackend.controller;

import com.taslim.springbootbackend.model.User;
import com.taslim.springbootbackend.service.UserService;
import com.taslim.springbootbackend.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
        Logger.info(user.toString());
        return userService.registerUser(user);
    }

    @PostMapping("login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody User user) {
        Logger.info(user.toString());
        return userService.loginUser(user);
    }
}
