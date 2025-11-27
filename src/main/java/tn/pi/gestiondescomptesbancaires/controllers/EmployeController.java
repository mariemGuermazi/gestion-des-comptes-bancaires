package tn.pi.gestiondescomptesbancaires.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.gestiondescomptesbancaires.entities.Client;
import tn.pi.gestiondescomptesbancaires.service.ClientService;
import tn.pi.gestiondescomptesbancaires.service.CompteService;
import tn.pi.gestiondescomptesbancaires.service.EmployeService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/employe")
public class EmployeController {

    private final ClientService clientService;
    private final CompteService compteService;
    private final EmployeService employeService;

    @GetMapping("/clients")
    public String clients(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        return "clients/list";
    }

    @GetMapping("/clients/add")
    public String addClientForm(Model model) {
        model.addAttribute("client", new Client());
        return "clients/form";
    }

    @PostMapping("/clients/save")
    public String saveClient(@ModelAttribute Client client) {
        clientService.saveClient(client);
        return "redirect:/employe/clients";
    }

    @GetMapping("/comptes")
    public String comptes(Model model) {
        model.addAttribute("comptes", compteService.getAll());
        return "comptes/list";
    }

    @GetMapping("/comptes/add/courant/{clientId}")
    public String addCourant(@PathVariable Long clientId) {
        compteService.createCompteCourant(clientId, 500); // valeur par défaut
        return "redirect:/employe/clients/" + clientId + "/comptes";
    }

    @GetMapping("/comptes/add/epargne/{clientId}")
    public String addEpargne(@PathVariable Long clientId) {
        compteService.createCompteEpargne(clientId, 0.02);
        return "redirect:/employe/clients/" + clientId + "/comptes";
    }

    @GetMapping("/clients/{id}/comptes")
    public String comptesClient(@PathVariable Long id, Model model) {
        model.addAttribute("client", clientService.getClient(id));
        model.addAttribute("comptes", compteService.getComptesByClient(id));
        return "comptes/client-comptes";
    }

    @GetMapping("/comptes/delete/{id}")
    public String deleteCompte(@PathVariable Long id) {
        compteService.deleteCompte(id);
        return "redirect:/employe/comptes";
    }
}
