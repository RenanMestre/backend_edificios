package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.api.service.EntregasService;
import org.springframework.ui.Model; 
import com.api.thymeleaf.Entregas;

@Controller
@RequestMapping("/entregas")
public class EntregasController {

    @Autowired
    private EntregasService service;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("entregas", service.listarTodos());
        return "entregas/lista"; // página HTML
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("entrega", new Entregas());
        return "entregas/form"; // página HTML
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Entregas entrega) {
        service.salvar(entrega);
        return "redirect:/entregas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("parceiro", service.buscarPorId(id));
        return "entregas/form";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        service.deletar(id);
        return "redirect:/entregas";
    }
}