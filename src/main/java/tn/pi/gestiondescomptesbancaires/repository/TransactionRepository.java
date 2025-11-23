package tn.pi.gestiondescomptesbancaires.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pi.gestiondescomptesbancaires.entities.Transaction;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByCompteId(Long compteId);
}
