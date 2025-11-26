/** package tn.pi.gestiondescomptesbancaires.model;

import jakarta.persistence.*;
import jakarta.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("EMPLOYE")
public class Employe extends Utilisateur {

    @NotBlank
    private String poste; // admin, conseiller, agent

    public Employe(String firstName, String lastName, String poste) {
        super();
        setFirstName(firstName);
        setLastName(lastName);
        this.poste = poste;
    }
}
**/