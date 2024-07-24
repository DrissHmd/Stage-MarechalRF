package com.marechalrf.marechalrfback.service;

import com.marechalrf.marechalrfback.dto.UserDto;
import com.marechalrf.marechalrfback.dto.mapper.UserMapper;
import com.marechalrf.marechalrfback.model.User;
import com.marechalrf.marechalrfback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserMapper userMapper, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<UserDto> getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::entityToDTO);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::entityToDTO)
                .collect(Collectors.toList());
    }

    public Optional<UserDto> getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::entityToDTO);
    }

    public UserDto createUser(UserDto userDto) {
        logger.info("Creating user with username: {}", userDto.getUsername());
        User user = userMapper.dtoToEntity(userDto);
        String rawPassword = userDto.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        logger.info("Raw password: {}", rawPassword);
        logger.info("Encoded password: {}", encodedPassword);
        logger.info("Double Encoded password: {}", passwordEncoder.encode(encodedPassword));

        user.setPassword(encodedPassword);
        User savedUser = userRepository.save(user);
        return userMapper.entityToDTO(savedUser);
    }

    public UserDto updateUser(Long id, UserDto userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setFirst_name(userDetails.getFirst_name());
        user.setLast_name(userDetails.getLast_name());
        user.setEmail(userDetails.getEmail());
        user.setPhone(userDetails.getPhone());
        user.setUsername(userDetails.getUsername());
        if (!userDetails.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        }
        User updatedUser = userRepository.save(user);
        return userMapper.entityToDTO(updatedUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
