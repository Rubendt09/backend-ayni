package com.ayni.backhacka.application.service;

import com.ayni.backhacka.domain.model.Client;
import com.ayni.backhacka.infraestructure.adapters.ports.out.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Optional<Client> findById(String id) {
        return clientRepository.findById(id);
    }

    public Client create(Client client) {
        if (clientRepository.existsById(client.getId())) {
            throw new IllegalArgumentException("Client with ID " + client.getId() + " already exists.");
        }
        return clientRepository.save(client);
    }

    public Client update(String id, Client client) {
        if (clientRepository.existsById(id)) {
            client.setId(id);
            return clientRepository.save(client);
        }
        return null;
    }

    public boolean delete(String id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return true;
        }
        return false;
    }
}