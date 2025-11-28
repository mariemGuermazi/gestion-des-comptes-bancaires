package tn.pi.gestiondescomptesbancaires.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.pi.gestiondescomptesbancaires.model.Client;
import tn.pi.gestiondescomptesbancaires.repository.ClientRepository;
import tn.pi.gestiondescomptesbancaires.service.ClientService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public Client getClientById(Long clientId) {
        return clientRepository.findById(clientId).orElse(null);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Long clientId, Client clientDetails) {
        Client existing = getClientById(clientId);

        if (existing == null) return null;

        existing.setNom(clientDetails.getNom());
        existing.setPrenom(clientDetails.getPrenom());
        existing.setEmail(clientDetails.getEmail());
        existing.setTelephone(clientDetails.getTelephone());
        existing.setMotDePasse(clientDetails.getMotDePasse());

        return clientRepository.save(existing);
    }

    @Override
    public void deleteClient(Long clientId) {
        clientRepository.deleteById(clientId);
    }

    @Override
    public Client getClientByEmail(String email) {
        return clientRepository.findByEmail(email).orElse(null);
    }

    @Override
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }
}
