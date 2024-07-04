package com.marechalrf.marechalrfback.service;

import com.marechalrf.marechalrfback.dto.UserDto;
import com.marechalrf.marechalrfback.dto.mapper.UserMapper;
import com.marechalrf.marechalrfback.model.Role;
import com.marechalrf.marechalrfback.model.User;
import com.marechalrf.marechalrfback.repository.RoleRepository;
import com.marechalrf.marechalrfback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public UserDto createUser(UserDto userDto) {
        User user = userMapper.dtoToEntity(userDto);
        user.setPassword(userDto.getPassword());
        User userToSave = userRepository.save(user);
        return userMapper.entityToDTO(userToSave);
    }

    public UserDto updateUser(Long id, UserDto userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        user.setPhone(userDetails.getPhone());
        user.setUsername(userDetails.getUsername());
        if (!userDetails.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        }
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
