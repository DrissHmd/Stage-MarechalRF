package com.marechalrf.marechalrfback.controller;

import com.marechalrf.marechalrfback.dto.UserDto;
import com.marechalrf.marechalrfback.model.Role;
import com.marechalrf.marechalrfback.model.User;
import com.marechalrf.marechalrfback.payload.LoginRequest;
import com.marechalrf.marechalrfback.service.UserService;
import com.marechalrf.marechalrfback.repository.RoleRepository;
import com.marechalrf.marechalrfback.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        Optional<UserDto> existingUser = userService.getUserByUsername(userDto.getUsername());
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("Username is already taken!");
        }

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        // Attribuer des rôles par défaut
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER");
        roles.add(userRole);
        userDto.setRoles(roles);

        UserDto createdUser = userService.createUser(userDto);
        return ResponseEntity.ok(Map.of("user", createdUser, "message", "User registered successfully!"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        Optional<UserDto> optionalUser = userService.getUserByUsername(loginRequest.getUsername());
        if (optionalUser.isPresent() && passwordEncoder.matches(loginRequest.getPassword(), optionalUser.get().getPassword())) {
            String token = jwtUtil.generateToken(loginRequest.getUsername());
            return ResponseEntity.ok(Map.of("token", token, "role", optionalUser.get().getRoles(), "message", "Login successful!"));
        }

        return ResponseEntity.badRequest().body("Invalid username or password!");
    }
}
