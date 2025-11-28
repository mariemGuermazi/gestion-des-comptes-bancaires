package tn.pi.gestiondescomptesbancaires.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false, unique = true)
    private String email;
    private String telephone;
    private String motDePasse;
    private String adresse;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Compte> comptes = new ArrayList<>();

    // Méthode utilitaire pour ajouter un compte
    public void addCompte(Compte compte) {
        comptes.add(compte);
        compte.setClient(this);
    }

    // Méthode utilitaire pour retirer un compte
    public void removeCompte(Compte compte) {
        comptes.remove(compte);
        compte.setClient(null);
    }
}