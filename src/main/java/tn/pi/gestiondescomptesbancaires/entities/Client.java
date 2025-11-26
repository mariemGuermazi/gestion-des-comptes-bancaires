package tn.pi.gestiondescomptesbancaires.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("CLIENT")
public class Client extends Utilisateur {

    @NotBlank
    @Email
    private String email;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Compte> comptes;

    public Client(String firstName, String lastName, String email) {
        super();
        setFirstName(firstName);
        setLastName(lastName);
        this.email = email;
    }
}
