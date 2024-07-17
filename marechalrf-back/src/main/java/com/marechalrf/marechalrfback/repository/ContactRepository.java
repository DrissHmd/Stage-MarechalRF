package com.marechalrf.marechalrfback.repository;

import com.marechalrf.marechalrfback.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
