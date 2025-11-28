package tn.pi.gestiondescomptesbancaires.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.gestiondescomptesbancaires.model.Client;
import tn.pi.gestiondescomptesbancaires.service.ClientService;

@Controller
@RequiredArgsConstructor
public class ClientLoginController {

    private final ClientService clientService;

    // 🔵 Redirection vers login par défaut
    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    // 🔵 Affichage page login
    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("client", new Client());
        return "auth/login";   // ✔️ bon chemin
    }

    // 🔵 Traitement login
    @PostMapping("/login")
    public String login(@ModelAttribute("client") Client client, Model model) {

        // Vérifier si email existe
        Client existing = clientService.getClientByEmail(client.getEmail());
        if (existing == null) {
            model.addAttribute("error", "Email non trouvé !");
            return "auth/login";   // ✔️ cohérent
        }

        // Vérifier mot de passe
        if (!existing.getMotDePasse().equals(client.getMotDePasse())) {
            model.addAttribute("error", "Mot de passe incorrect !");
            return "auth/login";
        }

        // 🔥 Connexion validée → redirection vers dashboard client
        return "redirect:/client/" + existing.getId() + "/dashboard";
    }
}
