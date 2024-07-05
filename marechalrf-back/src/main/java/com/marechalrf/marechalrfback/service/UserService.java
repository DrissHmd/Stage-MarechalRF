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

@Service
public class UserService {

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
        User user = userMapper.dtoToEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User savedUser = userRepository.save(user);
        return userMapper.entityToDTO(savedUser);
    }

    public UserDto updateUser(Long id, UserDto userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setFirstName(userDetails.getFirstname());
        user.setLastName(userDetails.getLastname());
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
