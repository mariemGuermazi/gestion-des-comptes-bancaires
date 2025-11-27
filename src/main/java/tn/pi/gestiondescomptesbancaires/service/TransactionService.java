package tn.pi.gestiondescomptesbancaires.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.pi.gestiondescomptesbancaires.model.*;
import tn.pi.gestiondescomptesbancaires.repository.TransactionRepository;
import tn.pi.gestiondescomptesbancaires.repository.CompteRepository;


import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final CompteRepository compteRepository;

    public List<Transaction> getByCompte(Long compteId) {
        return transactionRepository.findByCompteIdOrderByDateTransactionDesc(compteId);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Transactional
    public Transaction effectuerDepot(Long compteId, double montant) {
        if (montant <= 0) {
            throw new RuntimeException("Le montant doit être positif");
        }

        Compte compte = compteRepository.findById(compteId)
                .orElseThrow(() -> new RuntimeException("Compte introuvable"));

        // Mettre à jour le solde
        compte.setSolde(compte.getSolde() + montant);
        compteRepository.save(compte);

        // Créer la transaction
        Transaction transaction = new Transaction();
        transaction.setCompte(compte);
        transaction.setMontant(montant);
        transaction.setType(TypeTransaction.DEPOT);
        transaction.setDateTransaction(new Date());

        return transactionRepository.save(transaction);
    }

    @Transactional
    public Transaction effectuerRetrait(Long compteId, double montant) {
        if (montant <= 0) {
            throw new RuntimeException("Le montant doit être positif");
        }

        Compte compte = compteRepository.findById(compteId)
                .orElseThrow(() -> new RuntimeException("Compte introuvable"));

        // Vérifier le solde disponible
        double soldeDisponible = compte.getSolde();

        if (compte instanceof CompteCourant) {
            CompteCourant cc = (CompteCourant) compte;
            soldeDisponible += cc.getDecouvertAutorise();
        }

        if (montant > soldeDisponible) {
            throw new RuntimeException("Solde insuffisant pour effectuer ce retrait");
        }

        // Mettre à jour le solde
        compte.setSolde(compte.getSolde() - montant);
        compteRepository.save(compte);

        // Créer la transaction
        Transaction transaction = new Transaction();
        transaction.setCompte(compte);
        transaction.setMontant(montant);
        transaction.setType(TypeTransaction.RETRAIT);
        transaction.setDateTransaction(new Date());

        return transactionRepository.save(transaction);
    }

    @Transactional
    public Transaction effectuerVirement(Long compteSourceId, Long compteDestinationId, double montant) {
        if (montant <= 0) {
            throw new RuntimeException("Le montant doit être positif");
        }

        Compte compteSource = compteRepository.findById(compteSourceId)
                .orElseThrow(() -> new RuntimeException("Compte source introuvable"));

        Compte compteDestination = compteRepository.findById(compteDestinationId)
                .orElseThrow(() -> new RuntimeException("Compte destination introuvable"));

        // Vérifier le solde disponible
        double soldeDisponible = compteSource.getSolde();

        if (compteSource instanceof CompteCourant) {
            CompteCourant cc = (CompteCourant) compteSource;
            soldeDisponible += cc.getDecouvertAutorise();
        }

        if (montant > soldeDisponible) {
            throw new RuntimeException("Solde insuffisant pour effectuer ce virement");
        }

        // Débiter le compte source
        compteSource.setSolde(compteSource.getSolde() - montant);
        compteRepository.save(compteSource);

        // Créditer le compte destination
        compteDestination.setSolde(compteDestination.getSolde() + montant);
        compteRepository.save(compteDestination);

        // Créer la transaction de virement
        Transaction transaction = new Transaction();
        transaction.setCompte(compteSource);
        transaction.setMontant(montant);
        transaction.setType(TypeTransaction.VIREMENT);
        transaction.setDateTransaction(new Date());
        transaction.setCompteDestination(compteDestination);

        return transactionRepository.save(transaction);
    }

    public double calculerSoldeTotal(Long clientId) {
        return transactionRepository.findByCompteClientId(clientId)
                .stream()
                .mapToDouble(Transaction::getMontant)
                .sum();
    }

    public List<Transaction> getTransactionsByClient(Long clientId) {
        return transactionRepository.findByCompteClientId(clientId);
    }
}

