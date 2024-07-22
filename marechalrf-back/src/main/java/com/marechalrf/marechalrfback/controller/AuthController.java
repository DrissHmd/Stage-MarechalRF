package com.marechalrf.marechalrfback.controller;

import com.marechalrf.marechalrfback.dto.UserDto;
import com.marechalrf.marechalrfback.model.Role;
import com.marechalrf.marechalrfback.payload.LoginRequest;
import com.marechalrf.marechalrfback.service.UserService;
import com.marechalrf.marechalrfback.repository.RoleRepository;
import com.marechalrf.marechalrfback.util.JwtUtil;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        Optional<UserDto> existingUser = userService.getUserByUsername(userDto.getUsername());
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("Username is already taken!");
        }

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER");
        if (userRole == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Default role not found!");
        }
        roles.add(userRole);
        userDto.setRoles(roles);

        UserDto createdUser = userService.createUser(userDto);
        String token = jwtUtil.generateToken(createdUser.getUsername());

        return ResponseEntity.ok(Map.of("user", createdUser, "token", token, "message", "User registered successfully!"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        logger.info("Login attempt for user: {}", loginRequest.getUsername());
        Optional<UserDto> optionalUser = userService.getUserByUsername(loginRequest.getUsername());
        if (optionalUser.isPresent()) {
            logger.info("User found: {}", optionalUser.get().getUsername());

            String rawPassword = loginRequest.getPassword();
            String encodedPassword = optionalUser.get().getPassword();

            logger.info("Raw password: {}", rawPassword);
            logger.info("Encoded password: {}", encodedPassword);

            if (passwordEncoder.matches(rawPassword, encodedPassword)) {
                logger.info("Password match for user: {}", optionalUser.get().getUsername());
                String token = jwtUtil.generateToken(loginRequest.getUsername());
                logger.info("Token generated: {}", token);
                return ResponseEntity.ok(Map.of("token", token, "role", optionalUser.get().getRoles(), "message", "Login successful!"));
            } else {
                logger.warn("Password mismatch for user: {}", optionalUser.get().getUsername());
            }
        } else {
            logger.warn("User not found: {}", loginRequest.getUsername());
        }

        logger.info("Login failed for user: {}", loginRequest.getUsername());
        return ResponseEntity.badRequest().body("Invalid username or password!");
    }
}
