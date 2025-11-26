package tn.pi.gestiondescomptesbancaires.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.gestiondescomptesbancaires.entities.Client;
import tn.pi.gestiondescomptesbancaires.service.ClientService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        return "clients/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("client", new Client());
        return "clients/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Client client) {
        clientService.saveClient(client);
        return "redirect:/clients";
    }
}
