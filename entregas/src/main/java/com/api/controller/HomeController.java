package com.api.controller;

import com.api.repository.LoginRepository;
import com.api.repository.EntregasRepository;
import com.api.thymeleaf.Entregas;
import com.api.thymeleaf.Login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private EntregasRepository EntregasRepository;

    @GetMapping("/login")
    public String loginForm(Model model) {
        // opcional: passa mensagens para login.html
        model.addAttribute("erro", null);
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model
    ) {
        Login user = loginRepository.findByUsername(username);
        if (user != null) {
            String senhaBanco = user.getPassword();
            if (senhaBanco.equals(password)) {
                session.setAttribute("usuarioLogado", user);

                // redireciona conforme o tipo
                return switch (user.getTipo()) {
                    case "admin" -> "redirect:/admin";
                    case "recepcao" -> "redirect:/recepcao";
                    case "morador" -> "redirect:/moradores";
                    default -> "redirect:/index";
                };
            }
        }

        model.addAttribute("erro", "Usuário ou senha inválidos");
        return "login";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();  // Invalida a sessão atual
        }
        return "redirect:/login"; // Redireciona para a página de login (ou qualquer outra)
    }

    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/recepcao")
    public String recepcao(Model model) {
        model.addAttribute("entregas", EntregasRepository.findAll()); // Lista para exibir na página
        model.addAttribute("entrega", new Entregas()); // Objeto vazio para formulário
        return "recepcao";
    }

    @GetMapping({"/admin"})
    public String admin() {
        return "admin";
    }
}
