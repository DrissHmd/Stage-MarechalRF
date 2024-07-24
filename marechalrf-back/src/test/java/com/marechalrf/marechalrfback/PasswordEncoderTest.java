package com.marechalrf.marechalrfback;

import com.marechalrf.marechalrfback.controller.AuthController;
import com.marechalrf.marechalrfback.dto.UserDto;
import com.marechalrf.marechalrfback.payload.LoginRequest;
import com.marechalrf.marechalrfback.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PasswordEncoderTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthController authController;

    @Test
    public void testPasswordEncodingAndMatching() {
        String rawPassword = "testPassword";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        assertTrue(passwordEncoder.matches(rawPassword, encodedPassword));
    }

    @Test
    public void testUserPasswordHashingOnRegister() {
        String rawPassword = "testPassword123";
        UserDto userDto = new UserDto();
        userDto.setPassword(rawPassword);

        UserDto createdUser = userService.createUser(userDto);

        assertNotEquals(rawPassword, createdUser.getPassword());
        assertTrue(passwordEncoder.matches(rawPassword, createdUser.getPassword()));
    }

    @Test
    public void testUserLoginWithHashedPassword() {
        String rawPassword = "testPassword123";
        UserDto userDto = new UserDto();
        userDto.setUsername("testUser");
        userDto.setPassword(rawPassword);
        userService.createUser(userDto);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testUser");
        loginRequest.setPassword(rawPassword);

        ResponseEntity<?> response = authController.loginUser(loginRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
