package tn.pi.gestiondescomptesbancaires.service;

import tn.pi.gestiondescomptesbancaires.model.Client;
import java.util.List;

public interface ClientService {

    Client getClientById(Long clientId);

    List<Client> getAllClients();

    Client createClient(Client client);

    Client updateClient(Long clientId, Client clientDetails);

    void deleteClient(Long clientId);

    Client getClientByEmail(String email);

    Client saveClient(Client client);
}