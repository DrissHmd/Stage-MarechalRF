package com.marechalrf.marechalrfback.repository;

import com.marechalrf.marechalrfback.model.FormData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormRepository extends JpaRepository<FormData, Long> {
}
