package tn.pi.gestiondescomptesbancaires.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateOperation;
    private double montant;

    @Enumerated(EnumType.STRING)
    private TypeTransaction type;

    private String description;

    @ManyToOne
    private Compte compte;
}
