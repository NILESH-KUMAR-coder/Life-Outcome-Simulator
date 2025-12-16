package com.life.simulator.controller;

import com.life.simulator.dto.AuthRequest;
import com.life.simulator.dto.AuthResponse;
import com.life.simulator.entity.User;
import com.life.simulator.security.JwtTokenProvider;
import com.life.simulator.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth") // ✅ FIX
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider jwt;

    public AuthController(UserService userService, JwtTokenProvider jwt) {
        this.userService = userService;
        this.jwt = jwt;
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody AuthRequest req) {
        User user = userService.register(req.email, req.password);

        // Set the role for the user, here "USER" is hardcoded, you can change it based on your application needs
        String role = "USER";

        // Generate token with role
        return new AuthResponse(jwt.generateToken(user.getId(), user.getEmail(), role));
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest req) {
        User user = userService.authenticate(req.email, req.password);

        // Set the role for the user, here "USER" is hardcoded, you can change it based on your application needs
        String role = "USER";

        // Generate token with role
        return new AuthResponse(jwt.generateToken(user.getId(), user.getEmail(), role));
    }
}
