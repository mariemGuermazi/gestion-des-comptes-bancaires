package tn.pi.gestiondescomptesbancaires.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double montant;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dateTransaction;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeTransaction type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compte_id", nullable = false)
    private Compte compte;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compte_destination_id")
    private Compte compteDestination; // Pour les virements

    private String description;

    @PrePersist
    protected void onCreate() {
        if (dateTransaction == null) {
            dateTransaction = new Date();
        }
    }
}