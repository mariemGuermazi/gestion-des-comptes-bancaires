package tn.pi.gestiondescomptesbancaires.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pi.gestiondescomptesbancaires.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
