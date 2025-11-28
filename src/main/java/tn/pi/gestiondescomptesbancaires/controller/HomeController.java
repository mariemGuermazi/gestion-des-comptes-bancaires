package tn.pi.gestiondescomptesbancaires.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "redirect:/login";   // ⬅️ REDIRIGE PAR DÉFAUT
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";  // login.html dans templates/
    }

    @GetMapping("/employe/dashboard")
    public String dashboard() {
        return "dashboard";
    }
}
