package com.taslim.springbootbackend.service;

import com.google.common.base.Strings;
import com.taslim.springbootbackend.model.User;
import com.taslim.springbootbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public ResponseEntity<String> registerUser(User user) {

        if (checkBadRequest(user)) {
            return new ResponseEntity<>("Please enter email and password!.", HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!!");

    }

    public ResponseEntity<String> loginUser(User user) {

        if (checkBadRequest(user))
            return new ResponseEntity<>("Please enter email and password!.", HttpStatus.BAD_REQUEST);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                user.getEmail(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }

    public boolean checkBadRequest(User user) {
        if (Strings.isNullOrEmpty(user.getEmail()) && Strings.isNullOrEmpty(user.getPassword()))
            return true;
        return false;
    }
}
