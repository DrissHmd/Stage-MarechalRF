package com.marechalrf.marechalrfback.repository;

import com.marechalrf.marechalrfback.model.FileStorage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileStorageRepository extends JpaRepository<FileStorage, Long> {
    List<FileStorage> findByUserId(Long userId);
}
