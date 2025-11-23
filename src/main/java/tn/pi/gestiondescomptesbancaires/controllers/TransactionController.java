package tn.pi.gestiondescomptesbancaires.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.gestiondescomptesbancaires.entities.TypeTransaction;
import tn.pi.gestiondescomptesbancaires.service.TransactionService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/compte/{compteId}")
    public String listByCompte(@PathVariable Long compteId, Model model) {
        model.addAttribute("transactions", transactionService.getByCompte(compteId));
        return "transactions/list";
    }

    @PostMapping("/create")
    public String create(@RequestParam Long compteId,
                         @RequestParam double montant,
                         @RequestParam TypeTransaction type,
                         @RequestParam(required = false) String description) {
        transactionService.createTransaction(compteId, montant, type, description);
        return "redirect:/transactions/compte/" + compteId;
    }
}
