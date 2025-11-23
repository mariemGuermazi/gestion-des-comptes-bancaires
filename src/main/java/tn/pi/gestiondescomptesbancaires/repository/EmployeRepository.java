package tn.pi.gestiondescomptesbancaires.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pi.gestiondescomptesbancaires.entities.Employe;

public interface EmployeRepository extends JpaRepository<Employe, Long> {
}
