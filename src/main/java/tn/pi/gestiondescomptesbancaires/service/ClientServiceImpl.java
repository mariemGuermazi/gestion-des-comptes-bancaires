package tn.pi.gestiondescomptesbancaires.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.pi.gestiondescomptesbancaires.model.Client;
import tn.pi.gestiondescomptesbancaires.repository.ClientRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public Client getClientById(Long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client introuvable avec l'ID: " + clientId));
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client createClient(Client client) {
        if (clientRepository.existsByEmail(client.getEmail())) {
            throw new RuntimeException("Un client avec cet email existe déjà");
        }
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Long clientId, Client clientDetails) {
        Client client = getClientById(clientId);

        if (clientDetails.getNom() != null) {
            client.setNom(clientDetails.getNom());
        }
        if (clientDetails.getPrenom() != null) {
            client.setPrenom(clientDetails.getPrenom());
        }
        if (clientDetails.getEmail() != null) {
            // Vérifier si l'email n'est pas déjà utilisé par un autre client
            if (!client.getEmail().equals(clientDetails.getEmail()) &&
                    clientRepository.existsByEmail(clientDetails.getEmail())) {
                throw new RuntimeException("Un client avec cet email existe déjà");
            }
            client.setEmail(clientDetails.getEmail());
        }
        if (clientDetails.getTelephone() != null) {
            client.setTelephone(clientDetails.getTelephone());
        }
        if (clientDetails.getAdresse() != null) {
            client.setAdresse(clientDetails.getAdresse());
        }

        return clientRepository.save(client);
    }

    @Override
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(Long clientId) {
        Client client = getClientById(clientId);
        if (!client.getComptes().isEmpty()) {
            throw new RuntimeException("Impossible de supprimer un client avec des comptes actifs");
        }
        clientRepository.deleteById(clientId);
    }

    @Override
    public Client getClientByEmail(String email) {
        return clientRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Client introuvable avec l'email: " + email));
    }
}