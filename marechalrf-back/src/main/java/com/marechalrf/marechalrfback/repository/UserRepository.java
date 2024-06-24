package com.marechalrf.marechalrfback.repository;

import com.marechalrf.marechalrfback.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}