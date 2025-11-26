/**package tn.pi.gestiondescomptesbancaires.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.pi.gestiondescomptesbancaires.model.Client;
import tn.pi.gestiondescomptesbancaires.model.Compte;
import tn.pi.gestiondescomptesbancaires.service.ClientService;
import tn.pi.gestiondescomptesbancaires.service.CompteService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employe")
public class EmployeController {

    private final ClientService clientService;
    private final CompteService compteService;

    // -------- GESTION CLIENTS --------

    /**
     * Récupérer tous les clients
     */
  /**  @GetMapping("/clients")
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return ResponseEntity.ok(clients);
    }**/

    /**
     * Créer un client (payload JSON -> Client)
     * Remarque : si tu utilises des DTOs, remplace Client par ClientDTO et mappe côté service.
     */
 /**   @PostMapping("/clients")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client saved = clientService.saveClient(client);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }**/

    /**
     * Supprimer un client par id
     */
   /** @DeleteMapping("/clients/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        boolean existed = clientService.existsById(id);
        if (!existed) {
            return ResponseEntity.notFound().build();
        }
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }**/

    // -------- GESTION DES COMPTES --------

    /**
     * Créer un compte (payload JSON -> Compte)
     * Exemple: { "type":"COURANT", "solde":500.0, "clientId": 1 }
     */
   /** @PostMapping("/comptes")
    public ResponseEntity<Compte> createCompte(@RequestBody Compte compte) {
        Compte created = compteService.createCompte(compte);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }**/

    /**
     * Mettre à jour un compte existant
     */
   /** @PutMapping("/comptes/{id}")
    public ResponseEntity<Compte> updateCompte(@PathVariable Long id, @RequestBody Compte compte) {
        Optional<Compte> updatedOpt = compteService.updateCompte(id, compte);
        return updatedOpt
                .map(c -> ResponseEntity.ok(c))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
**/