package tn.pi.gestiondescomptesbancaires.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import tn.pi.gestiondescomptesbancaires.model.Client;
import tn.pi.gestiondescomptesbancaires.service.ClientService;
import tn.pi.gestiondescomptesbancaires.service.CompteService;
import tn.pi.gestiondescomptesbancaires.service.TransactionService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientDashboardController {

    private final ClientService clientService;
    private final CompteService compteService;
    private final TransactionService transactionService;

    // 🟦 Tableau de bord client
    @GetMapping("/{clientId}/dashboard")
    public String dashboard(@PathVariable Long clientId, Model model) {
        try {
            model.addAttribute("client", clientService.getClientById(clientId));
            model.addAttribute("comptes", compteService.getComptesByClient(clientId));
            return "client/dashboard";
        } catch (RuntimeException e) {
            model.addAttribute("error", "Client introuvable");
            return "error";
        }
    }

    // 🟦 Voir les comptes
    @GetMapping("/{clientId}/comptes")
    public String comptes(@PathVariable Long clientId, Model model) {
        try {
            model.addAttribute("client", clientService.getClientById(clientId));
            model.addAttribute("comptes", compteService.getComptesByClient(clientId));
            return "client/comptes";
        } catch (RuntimeException e) {
            model.addAttribute("error", "Impossible de récupérer les comptes");
            return "error";
        }
    }

    // 🟦 Voir les transactions d'un compte
    @GetMapping("/compte/{compteId}/transactions")
    public String transactions(@PathVariable Long compteId, Model model) {
        try {
            model.addAttribute("compte", compteService.getCompteById(compteId));
            model.addAttribute("transactions", transactionService.getByCompte(compteId));
            return "client/transactions";
        } catch (RuntimeException e) {
            model.addAttribute("error", "Compte introuvable");
            return "error";
        }
    }

    // 🟦 Page profil
    @GetMapping("/{clientId}/profile")
    public String profile(@PathVariable Long clientId, Model model) {
        try {
            model.addAttribute("client", clientService.getClientById(clientId));
            return "client/profile";
        } catch (RuntimeException e) {
            model.addAttribute("error", "Profil introuvable");
            return "error";
        }
    }

    // 🟦 Modifier le profil
    @PostMapping("/{clientId}/profile")
    public String updateProfile(@PathVariable Long clientId,
                                @ModelAttribute("client") Client client,
                                Model model) {
        try {
            clientService.updateClient(clientId, client);
            model.addAttribute("success", "Profil mis à jour avec succès");
            return "redirect:/client/" + clientId + "/profile";
        } catch (RuntimeException e) {
            model.addAttribute("error", "Erreur lors de la mise à jour");
            return "client/profile";
        }
    }
}