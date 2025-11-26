package tn.pi.gestiondescomptesbancaires.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.pi.gestiondescomptesbancaires.model.Compte;
import tn.pi.gestiondescomptesbancaires.model.Client;
import tn.pi.gestiondescomptesbancaires.repository.CompteRepository;
import tn.pi.gestiondescomptesbancaires.repository.ClientRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompteService {

    private final CompteRepository compteRepository;
    private final ClientRepository clientRepository;

    @Transactional
    public Compte createCompteForClient(Long clientId, Compte compte) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client introuvable avec l'ID: " + clientId));

        compte.setClient(client);
        compte.setDateCreation(new java.util.Date());

        return compteRepository.save(compte);
    }

    public List<Compte> getComptesByClient(Long clientId) {
        if (!clientRepository.existsById(clientId)) {
            throw new RuntimeException("Client introuvable avec l'ID: " + clientId);
        }
        return compteRepository.findByClientId(clientId);
    }

    public Compte getCompteById(Long compteId) {
        return compteRepository.findById(compteId)
                .orElseThrow(() -> new RuntimeException("Compte introuvable avec l'ID: " + compteId));
    }

    @Transactional
    public void deleteCompte(Long compteId) {
        Compte compte = getCompteById(compteId);
        if (compte.getSolde() != 0) {
            throw new RuntimeException("Impossible de supprimer un compte avec un solde non nul");
        }
        compteRepository.deleteById(compteId);
    }

    public double getSolde(Long compteId) {
        return getCompteById(compteId).getSolde();
    }

    @Transactional
    public void updateSolde(Long compteId, double montant) {
        Compte compte = getCompteById(compteId);
        compte.setSolde(compte.getSolde() + montant);
        compteRepository.save(compte);
    }
}

