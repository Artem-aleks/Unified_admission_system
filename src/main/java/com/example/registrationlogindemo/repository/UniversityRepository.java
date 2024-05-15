package com.example.registrationlogindemo.repository;

import com.example.registrationlogindemo.entity.ListUniversity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepository extends JpaRepository<ListUniversity, Long> {
}
