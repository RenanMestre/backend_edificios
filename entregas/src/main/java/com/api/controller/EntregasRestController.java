package com.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.service.EntregasService;
import com.api.thymeleaf.Entregas;

@RestController
@RequestMapping("/api/entregas")
public class EntregasRestController {
    private final EntregasService service;
    public EntregasRestController(EntregasService service) { this.service = service; }

    @GetMapping
    public List<Entregas> listar() { return service.listarTodos(); }

    @GetMapping("/{id}")
    public ResponseEntity<Entregas> buscar(@PathVariable Long id) {
        Entregas e = service.buscarPorId(id);
        return e != null ? ResponseEntity.ok(e) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Entregas criar(@RequestBody Entregas entrega) { return service.salvar(entrega); }

    @PutMapping("/{id}")
    public ResponseEntity<Entregas> atualizar(@PathVariable Long id, @RequestBody Entregas nova) {
        Entregas exist = service.buscarPorId(id);
        if (exist == null) return ResponseEntity.notFound().build();
        exist.setEntregador(nova.getEntregador());
        exist.setDestinatario(nova.getDestinatario());
        exist.setHora(nova.getHora());
        exist.setLoja(nova.getLoja());
        exist.setStatus(nova.getStatus());
        return ResponseEntity.ok(service.salvar(exist));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
