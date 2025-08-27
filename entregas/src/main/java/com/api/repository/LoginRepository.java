package com.api.repository;

import com.api.thymeleaf.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
    
    // Método customizado para buscar um usuário pelo nome de usuário
    Login findByUsername(String username);
}