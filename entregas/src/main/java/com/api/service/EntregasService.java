package com.api.service;

import com.api.thymeleaf.Entregas;
import com.api.repository.EntregasRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntregasService {
    
    @Autowired
    private EntregasRepository repository;

    public List<Entregas> listarTodos() {
        return repository.findAll();
    }

    public Entregas salvar(Entregas entregas) {
        return repository.save(entregas);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public Entregas buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }
}
