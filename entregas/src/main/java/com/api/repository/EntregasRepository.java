package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.thymeleaf.Entregas;

public interface EntregasRepository extends JpaRepository<Entregas, Long> {
}

