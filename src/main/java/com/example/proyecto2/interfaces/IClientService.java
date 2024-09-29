package com.example.proyecto2.interfaces;

import com.example.proyecto2.domain.Client;
import lombok.NonNull;

import java.util.List;

public interface IClientService {
    void createClient(@NonNull Client client);

    List<Client> getClients();

    void deleteClient(@NonNull Long clientId);

    void updateClient(@NonNull Client client);

    Client getClient(@NonNull Long clientId);

}
