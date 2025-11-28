package tn.pi.gestiondescomptesbancaires.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.gestiondescomptesbancaires.model.Client;
import tn.pi.gestiondescomptesbancaires.service.ClientService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/client")  // Ajout pour toutes les routes client
public class ClientLoginController {

    private final ClientService clientService;

    // 🔵 Affichage page login
    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("client", new Client());
        return "/login";
    }

    // 🔵 Traitement login
    @PostMapping("/login")
    public String login(@ModelAttribute("client") Client client, Model model) {

        Client existing = clientService.getClientByEmail(client.getEmail());
        if (existing == null) {
            model.addAttribute("error", "Email non trouvé !");
            return "/login";
        }

        if (!existing.getMotDePasse().equals(client.getMotDePasse())) {
            model.addAttribute("error", "Mot de passe incorrect !");
            return "/login";
        }

        // 🔥 Redirection vers dashboard client
        return "redirect:/client/" + existing.getId() + "/dashboard";
    }

    // 🔵 Dashboard client
    @GetMapping("/{id}/dashboard")
    public String clientDashboard(@PathVariable Long id, Model model) {
        Client client = clientService.getClientById(id);
        model.addAttribute("client", client);
        return "client/dashboard"; // client/dashboard.html
    }
}
