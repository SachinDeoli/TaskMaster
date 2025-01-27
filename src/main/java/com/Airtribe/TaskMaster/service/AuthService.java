package com.Airtribe.TaskMaster.service;

import com.Airtribe.TaskMaster.JwtUtil.JwtUtil;
import com.Airtribe.TaskMaster.entity.User;
import com.Airtribe.TaskMaster.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public void login(String username, String password) {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new UsernameNotFoundException("Invalid password");
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public String generateToken(String username) {
        return JwtUtil.generateToken(username);
    }
}
