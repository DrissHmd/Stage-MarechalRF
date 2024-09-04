package com.marechalrf.marechalrfback.controller;

import com.marechalrf.marechalrfback.dto.UserDto;
import com.marechalrf.marechalrfback.exception.InvalidCredentialsException;
import com.marechalrf.marechalrfback.exception.UserNotFoundException;
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

import java.time.LocalDate;
import java.util.*;

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
        try {
            Optional<UserDto> existingUser = userService.getUserByUsername(userDto.getUsername());

            if (existingUser.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is already taken!");
            }

            // Find the role and set it
            Role userRole = roleRepository.findByName("ROLE_USER");
            if (userRole == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Default role not found!");
            }
            userDto.setRoleId(userRole.getId());
            userDto.setAssignedDate(LocalDate.now());

            UserDto createdUser = userService.createUser(userDto);
            String token = jwtUtil.generateToken(createdUser.getUsername());

            Map<String, Object> response = new HashMap<>();
            response.put("user", createdUser);
            response.put("token", token);
            response.put("message", "User registered successfully!");

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during registration");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {
        try {
            Optional<UserDto> optionalUser = userService.getUserByUsername(userDto.getUsername());

            if (optionalUser.isEmpty()) {
                throw new UserNotFoundException("User not found");
            }

            UserDto user = optionalUser.get();
            logger.info("User Password {}",user.getPassword());
            logger.info("UserDto Password {}",userDto.getPassword());

            if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
                throw new InvalidCredentialsException("Invalid password");
            }

            String token = jwtUtil.generateToken(user.getUsername());
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("role", user.getRoleId());
            response.put("message", "Login successful");

            return ResponseEntity.ok(response);
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (InvalidCredentialsException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login");
        }
    }

    @PostMapping("/send-verification-code")
    public ResponseEntity<?> sendVerificationCode(@RequestParam String email) {
        Optional<UserDto> optionalUser = userService.getUserByEmail(email);

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with this email");
        }

        String verificationCode = generateVerificationCode();
        userService.saveVerificationCode(email, verificationCode);
        userService.sendVerificationEmail(email, verificationCode);

        return ResponseEntity.ok("Verification code sent to your email");
    }

    @PostMapping("/verify-code")
    public ResponseEntity<?> verifyCode(@RequestParam String email, @RequestParam String code) {
        try {
            boolean isValid = userService.isVerificationCodeValid(email, code);
            if (isValid) {
                return ResponseEntity.ok("Verification successful");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired verification code");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    private String generateVerificationCode() {
        return UUID.randomUUID().toString().substring(0, 6);  // Génère un code de 6 caractères
    }


}
