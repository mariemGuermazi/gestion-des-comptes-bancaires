package tn.pi.gestiondescomptesbancaires.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import tn.pi.gestiondescomptesbancaires.model.Transaction;
import tn.pi.gestiondescomptesbancaires.model.TypeTransaction;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByCompteIdOrderByDateTransactionDesc(Long compteId);
    List<Transaction> findByCompteClientId(Long clientId);
    List<Transaction> findByType(TypeTransaction type);
    List<Transaction> findByDateTransactionBetween(Date dateDebut, Date dateFin);
    List<Transaction> findByCompteIdAndDateTransactionBetween(Long compteId, Date dateDebut, Date dateFin);
}