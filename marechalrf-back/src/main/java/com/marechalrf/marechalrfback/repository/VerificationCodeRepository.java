package com.marechalrf.marechalrfback.repository;

import com.marechalrf.marechalrfback.model.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {
    Optional<VerificationCode> findByToken(String token);
    void deleteByExpirationDateBefore(java.time.LocalDateTime now);
    Optional<VerificationCode> findByUserId(Long userId);
}
