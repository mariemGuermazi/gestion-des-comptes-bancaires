package tn.pi.gestiondescomptesbancaires.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_compte", discriminatorType = DiscriminatorType.STRING)
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String numeroCompte;

    @Column(nullable = false)
    private double solde;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToMany(mappedBy = "compte", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> transactions;

    @PrePersist
    protected void onCreate() {
        if (dateCreation == null) {
            dateCreation = new Date();
        }
        if (numeroCompte == null) {
            numeroCompte = generateNumeroCompte();
        }
    }

    private String generateNumeroCompte() {
        return "CPT" + System.currentTimeMillis();
    }
}