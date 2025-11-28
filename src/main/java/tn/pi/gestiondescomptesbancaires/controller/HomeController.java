package tn.pi.gestiondescomptesbancaires.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 🔵 Redirection par défaut vers login
    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    // 🔵 Dashboard employé (existant, pas de conflit)
    @GetMapping("/employe/dashboard")
    public String dashboard() {
        return "dashboard"; // dashboard.html
    }
}
