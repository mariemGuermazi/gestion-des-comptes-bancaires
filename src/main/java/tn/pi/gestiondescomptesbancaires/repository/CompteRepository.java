package tn.pi.gestiondescomptesbancaires.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pi.gestiondescomptesbancaires.entities.Compte;

import java.util.List;

public interface CompteRepository extends JpaRepository<Compte, Long> {
    List<Compte> findByClientId(Long clientId);
}
