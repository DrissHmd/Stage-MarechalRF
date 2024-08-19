package com.marechalrf.marechalrfback.controller;

import com.marechalrf.marechalrfback.dto.UserDto;
import com.marechalrf.marechalrfback.dto.response.Response;
import com.marechalrf.marechalrfback.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PutMapping("/{id}")
    public ResponseEntity<Response<UserDto>> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        Callable<UserDto> f = () -> userService.updateUser(id, userDto);
        return ResponseController.createResponseEntity(f, LOGGER, null);
    }

    @GetMapping("")
    public ResponseEntity<Response<List<UserDto>>> listUsers() {
        Callable<List<UserDto>> f = () -> userService.getAllUsers();
        return ResponseController.createResponseEntity(f, LOGGER, new ArrayList<>());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<UserDto>> getUser(@PathVariable Long id) {
        Callable<UserDto> f = () -> {
            Optional<UserDto> userOptional = userService.getUserById(id);
            if (userOptional.isPresent()) {
                return userOptional.get();
            } else {
                throw new RuntimeException("User not found");
            }
        };
        return ResponseController.createResponseEntity(f, LOGGER, null);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getUsersByUsername(@RequestParam String name) {
        Optional<UserDto> userDto = userService.getUserByUsername(name);
        if (userDto.isPresent()) {
            return ResponseEntity.ok(userDto.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Aucun utilisateur trouvé avec le nom d'utilisateur: " + name);
        }
    }

    @PostMapping("")
    public ResponseEntity<Response<UserDto>> saveUser(@RequestBody UserDto userDto) {
        Callable<UserDto> f = () -> userService.createUser(userDto);
        return ResponseController.createResponseEntity(f, LOGGER, null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> deleteUser(@PathVariable Long id) {
        Callable<Void> f = () -> {
            userService.deleteUser(id);
            return null;
        };
        return ResponseController.createResponseEntity(f, LOGGER, null);
    }
}
