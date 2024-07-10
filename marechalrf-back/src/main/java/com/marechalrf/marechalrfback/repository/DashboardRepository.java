package com.marechalrf.marechalrfback.repository;

import com.marechalrf.marechalrfback.model.Dashboard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DashboardRepository extends JpaRepository<Dashboard, Long> {
}