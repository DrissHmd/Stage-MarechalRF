package com.marechalrf.marechalrfback.service;

import com.marechalrf.marechalrfback.dto.UserDto;
import com.marechalrf.marechalrfback.dto.mapper.UserMapper;
import com.marechalrf.marechalrfback.model.Role;
import com.marechalrf.marechalrfback.model.User;
import com.marechalrf.marechalrfback.model.VerificationCode;
import com.marechalrf.marechalrfback.repository.RoleRepository;
import com.marechalrf.marechalrfback.repository.UserRepository;
import com.marechalrf.marechalrfback.repository.VerificationCodeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private VerificationCodeRepository tokenRepository;
    @Autowired
    private VerificationCodeRepository verificationCodeRepository;

    @Autowired
    public UserService(UserMapper userMapper, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<UserDto> getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(user -> {
                    logger.info("Id for user {}: {}", username, user.getId());
                    return user;
                })
                .map(userMapper::entityToDTO)
                .map(userDto -> {
                    logger.info("Id for userDto {}: {}", username, userDto.getId());
                    return userDto;
                });
    }

    public List<UserDto> findByContaining(String query) {
        List<User> users = userRepository.findByContaining(query);
        return users.stream()
                .map(userMapper::entityToDTO)
                .collect(Collectors.toList());
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
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setAssigned_date(LocalDate.now());
        Role role = roleRepository.findById(userDto.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRole(role);
        User savedUser = userRepository.save(user);
        return userMapper.entityToDTO(savedUser);
    }

    @Transactional
    public UserDto updateUser(Long id, UserDto userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setFirst_name(userDetails.getFirst_name());
        user.setLast_name(userDetails.getLast_name());
        user.setEmail(userDetails.getEmail());
        user.setPhone(userDetails.getPhone());
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        if (userDetails.getRoleId() != null) {
            Role role = roleRepository.findById(userDetails.getRoleId())
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            user.setRole(role);
        }
        User updatedUser = userRepository.save(user);
        return userMapper.entityToDTO(updatedUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<UserDto> getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::entityToDTO);
    }

    public void saveVerificationCode(UserDto user, String code) {
        LocalDateTime expirationDate = LocalDateTime.now().plusMinutes(15);  // Expire dans 15 minutes
        VerificationCode verificationCode = new VerificationCode(code, expirationDate, userMapper.dtoToEntity(user));
        verificationCodeRepository.save(verificationCode);
    }

    public void sendVerificationEmail(UserDto user, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Email Verification");
        message.setText("Votre code de vérification est : " + token);
        mailSender.send(message);
    }

    public boolean verifyCode(String token) {
        Optional<VerificationCode> verificationCode = verificationCodeRepository.findByToken(token);
        return verificationCode.isPresent() && verificationCode.get().getExpirationDate().isAfter(LocalDateTime.now());
    }

    public void deleteExpiredCodes() {
        verificationCodeRepository.deleteByExpirationDateBefore(LocalDateTime.now());
    }
}
