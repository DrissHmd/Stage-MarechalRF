package com.marechalrf.marechalrfback.repository;

import com.marechalrf.marechalrfback.model.Widget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WidgetRepository extends JpaRepository<Widget, Long> {
}