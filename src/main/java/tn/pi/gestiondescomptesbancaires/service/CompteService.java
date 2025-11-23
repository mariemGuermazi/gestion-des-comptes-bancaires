package tn.pi.gestiondescomptesbancaires.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.pi.gestiondescomptesbancaires.entities.*;
import tn.pi.gestiondescomptesbancaires.entities.Client;
import tn.pi.gestiondescomptesbancaires.entities.Compte;
import tn.pi.gestiondescomptesbancaires.entities.CompteCourant;
import tn.pi.gestiondescomptesbancaires.entities.CompteEpargne;
import tn.pi.gestiondescomptesbancaires.repository.ClientRepository;
import tn.pi.gestiondescomptesbancaires.repository.CompteRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompteService {

    private final CompteRepository compteRepository;
    private final ClientRepository clientRepository;

    public List<Compte> getAll() { return compteRepository.findAll(); }

    public List<Compte> getComptesByClient(Long clientId) { return compteRepository.findByClientId(clientId); }

    public Compte createCompteCourant(Long clientId, double decouvert) {
        Client c = clientRepository.findById(clientId).orElseThrow();
        CompteCourant cc = new CompteCourant();
        cc.setClient(c);
        cc.setNumeroCompte("CC-" + System.currentTimeMillis());
        cc.setSolde(0);
        cc.setDateCreation(new Date());
        cc.setDecouvertAutorise(decouvert);
        return compteRepository.save(cc);
    }

    public Compte createCompteEpargne(Long clientId, double taux) {
        Client c = clientRepository.findById(clientId).orElseThrow();
        CompteEpargne ce = new CompteEpargne();
        ce.setClient(c);
        ce.setNumeroCompte("CE-" + System.currentTimeMillis());
        ce.setSolde(0);
        ce.setDateCreation(new Date());
        ce.setTauxInteret(taux);
        return compteRepository.save(ce);
    }

    public Compte getCompte(Long id) { return compteRepository.findById(id).orElse(null); }
    public void deleteCompte(Long id) { compteRepository.deleteById(id); }

    public void deposer(Long id, double montant) {
        Compte c = compteRepository.findById(id).orElseThrow();
        c.setSolde(c.getSolde() + montant);
        compteRepository.save(c);
    }

    public void retirer(Long id, double montant) {
        Compte c = compteRepository.findById(id).orElseThrow();
        c.setSolde(c.getSolde() - montant);
        compteRepository.save(c);
    }
}
