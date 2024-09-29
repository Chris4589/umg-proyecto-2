package com.example.proyecto2.service;

import com.example.proyecto2.domain.Client;
import com.example.proyecto2.exception.BadRequestException;
import com.example.proyecto2.exception.NoContentException;
import com.example.proyecto2.interfaces.IClientService;
import com.example.proyecto2.repository.ClientRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService implements IClientService {
    private final ClientRepository clientRepository;

    public void createClient(@NonNull Client client) {
        client.validate();

        this.clientRepository.findByIssuer(client.getIssuer())
                .ifPresent(c -> {
                    throw new BadRequestException("The issuer already exists");
                });

        this.clientRepository.save(client);
    }

    @Override
    public List<Client> getClients() {
        var clients = this.clientRepository.findAll();
        if (clients.isEmpty()) {
            throw new NoContentException("There are no clients");
        }
        return clients;
    }

    @Override
    public void deleteClient(@NonNull Long clientId) {
        if (clientId <= 0) {
            throw new BadRequestException("The client id is required");
        }

        var client = this.clientRepository.findById(clientId)
                .orElse(null);

        if (client == null) {
            throw new NoContentException("The client does not exist");
        }

        this.clientRepository.deleteById(clientId);
    }

    @Override
    public void updateClient(@NonNull Client client) {
        client.validate();
        client.validateClientId();

        var clientToUpdate = this.clientRepository.findById(client.getClientId())
                .orElse(null);

        if (clientToUpdate == null) {
            throw new NoContentException("The client does not exist");
        }
        this.clientRepository.save(client);
    }

    @Override
    public Client getClient(@NonNull Long clientId) {
        var client = this.clientRepository.findById(clientId)
                .orElse(null);

        if (client == null) {
            throw new NoContentException("The client does not exist");
        }
        return client;
    }


}
