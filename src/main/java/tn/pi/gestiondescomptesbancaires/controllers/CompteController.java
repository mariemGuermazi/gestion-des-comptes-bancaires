package tn.pi.gestiondescomptesbancaires.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.gestiondescomptesbancaires.service.CompteService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comptes")
public class CompteController {

    private final CompteService compteService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("comptes", compteService.getAll());
        return "comptes/list";
    }
}
