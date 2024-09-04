package com.marechalrf.marechalrfback.repository;

import com.marechalrf.marechalrfback.dto.UserDto;
import com.marechalrf.marechalrfback.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.first_name LIKE %:query% OR u.last_name LIKE %:query%")
    List<User> findByContaining(String query);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}