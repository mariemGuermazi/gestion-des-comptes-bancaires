package tn.pi.gestiondescomptesbancaires.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.pi.gestiondescomptesbancaires.entities.Client;
import tn.pi.gestiondescomptesbancaires.repository.ClientRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public List<Client> getAllClients() { return clientRepository.findAll(); }
    public Client saveClient(Client c) { return clientRepository.save(c); }
    public Client getClient(Long id) { return clientRepository.findById(id).orElse(null); }
    public void deleteClient(Long id) { clientRepository.deleteById(id); }
}
