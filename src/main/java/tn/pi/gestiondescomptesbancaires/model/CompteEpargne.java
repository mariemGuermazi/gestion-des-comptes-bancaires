package tn.pi.gestiondescomptesbancaires.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("EPARGNE")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CompteEpargne extends Compte {

    private double tauxInteret; // Taux d'intérêt annuel (ex: 2.5 pour 2.5%)

    // Méthode pour calculer les intérêts
    public double calculerInterets() {
        return getSolde() * (tauxInteret / 100);
    }

    // Méthode pour appliquer les intérêts au compte
    public void appliquerInterets() {
        double interets = calculerInterets();
        setSolde(getSolde() + interets);
    }
}