package com.Airtribe.TaskMaster.controller;

import com.Airtribe.TaskMaster.entity.User;
import com.Airtribe.TaskMaster.repository.UserRepo;
import com.Airtribe.TaskMaster.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        User us = authService.register(user);
        return ResponseEntity.ok(us);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam("username") String username, @RequestParam("password") String password){
        authService.login(username, password);
        String token = authService.generateToken(username);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "User authenticated successfully!");
        response.put("token", token);

        return ResponseEntity.ok(response);
    }
}
