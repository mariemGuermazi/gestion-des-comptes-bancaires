package tn.pi.gestiondescomptesbancaires.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.pi.gestiondescomptesbancaires.entities.*;
import tn.pi.gestiondescomptesbancaires.entities.Compte;
import tn.pi.gestiondescomptesbancaires.entities.Transaction;
import tn.pi.gestiondescomptesbancaires.entities.TypeTransaction;
import tn.pi.gestiondescomptesbancaires.repository.TransactionRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CompteService compteService;

    public Transaction createTransaction(Long compteId, double montant, TypeTransaction type, String description) {
        Compte compte = compteService.getCompte(compteId);
        if (compte == null) throw new IllegalArgumentException("Compte introuvable");
        Transaction t = new Transaction();
        t.setCompte(compte);
        t.setDateOperation(new Date());
        t.setMontant(montant);
        t.setType(type);
        t.setDescription(description);

        // appliquer au compte
        if (type == TypeTransaction.CREDIT) {
            compte.setSolde(compte.getSolde() + montant);
        } else if (type == TypeTransaction.DEBIT) {
            compte.setSolde(compte.getSolde() - montant);
        }
        // save transaction (compte saved by CompteService if you call it)
        return transactionRepository.save(t);
    }

    public List<Transaction> getByCompte(Long compteId) { return transactionRepository.findByCompteId(compteId); }
}
