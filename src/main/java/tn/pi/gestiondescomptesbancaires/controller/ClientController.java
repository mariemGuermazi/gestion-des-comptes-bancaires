package tn.pi.gestiondescomptesbancaires.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.gestiondescomptesbancaires.model.Client;
import tn.pi.gestiondescomptesbancaires.service.ClientService;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // Liste des clients
    @GetMapping
    public String listClients(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        return "clients/list";
    }

    // Formulaire d’ajout
    @GetMapping("/add")
    public String addClientForm(Model model) {
        model.addAttribute("client", new Client());
        return "clients/add";
    }

    // Sauvegarder un client
    @PostMapping("/save")
    public String saveClient(@ModelAttribute("client") Client client) {
        clientService.saveClient(client);
        return "redirect:/clients";
    }

    // Formulaire de modification
    @GetMapping("/edit/{id}")
    public String editClient(@PathVariable Long id, Model model) {
        model.addAttribute("client", clientService.getClientById(id));
        return "clients/edit";
    }

    // Suppression
    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return "redirect:/clients";
    }
}
