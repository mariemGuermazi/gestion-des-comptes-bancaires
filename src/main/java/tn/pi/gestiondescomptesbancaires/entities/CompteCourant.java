package tn.pi.gestiondescomptesbancaires.entities;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CompteCourant extends Compte {
    private double decouvertAutorise;
}
