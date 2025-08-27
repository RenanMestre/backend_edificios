package com.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.api.repository.EntregasRepository;
import com.api.service.EntregasService;
import com.api.thymeleaf.Entregas;

@Controller
public class EdificioController {

    private final EntregasRepository entregasRepository;
    private final EntregasService entregasService;

    // ✅ Injeção via construtor
    public EdificioController(EntregasRepository entregasRepository, EntregasService entregasService) {
        this.entregasRepository = entregasRepository;
        this.entregasService = entregasService;
    }

    // ========== MORADORES ==========
    @GetMapping({"/moradores"})
    public String moradores(Model model) {
        model.addAttribute("entregas", entregasRepository.findAll());
        return "moradores";
    }

    // ========== ENTREGAS ==========
    @PostMapping("/recepcao")
    public String salvarEntrega(@ModelAttribute Entregas entrega, Model model) {
        entregasRepository.save(entrega);
        return "redirect:/recepcao";
    }

    @GetMapping("/editar-entrega/{id}")
    public String mostrarFormularioEdicao(@PathVariable("id") Long id, Model model) {
        Entregas entrega = entregasService.buscarPorId(id);
        if (entrega != null) {
            model.addAttribute("entrega", entrega);
            return "editar_entrega";
        } else {
            return "redirect:/recepcao";
        }
    }

    @PostMapping("/atualizar-entrega")
    public String atualizarEntrega(@ModelAttribute Entregas entrega) {
        entregasService.salvar(entrega); // salvar faz update se o ID já existir
        return "redirect:/recepcao";
    }


    @GetMapping("/deletar-entrega/{id}")
    public String deletarEntrega(@PathVariable("id") Long id) {
        entregasRepository.deleteById(id);
        return "redirect:/recepcao";
    }
}
