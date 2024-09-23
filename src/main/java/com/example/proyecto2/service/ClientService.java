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


}
