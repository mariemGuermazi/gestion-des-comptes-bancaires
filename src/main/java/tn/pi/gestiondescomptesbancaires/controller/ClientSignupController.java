package tn.pi.gestiondescomptesbancaires.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.gestiondescomptesbancaires.model.Client;
import tn.pi.gestiondescomptesbancaires.service.ClientService;

@Controller
@RequiredArgsConstructor
public class ClientSignupController {

    private final ClientService clientService;

    // 🔵 Page signup
    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("client", new Client());
        return "/signup";
    }

    // 🔵 Traitement signup
    @PostMapping("/signup")
    public String signup(@ModelAttribute("client") Client client, Model model) {

        try {
            clientService.createClient(client);
            model.addAttribute("success", "Compte créé avec succès !");
            return "redirect:/login";

        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "signup";
        }
    }
}
