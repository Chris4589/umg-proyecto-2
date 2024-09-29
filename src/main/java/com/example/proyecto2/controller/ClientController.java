package com.example.proyecto2.controller;

import com.example.proyecto2.domain.Client;
import com.example.proyecto2.interfaces.IClientService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/client")
@RestController
@RequiredArgsConstructor
public class ClientController {
    private final IClientService clientService;

    @PostMapping("/")
    public ResponseEntity<Client> createClient(@NonNull @RequestBody Client client) {
        this.clientService.createClient(client);
        return ResponseEntity.ok(client);
    }

    @GetMapping("/")
    public ResponseEntity<List<Client>> getClients() {
        return ResponseEntity.ok(this.clientService.getClients());
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> deleteClient(@NonNull @PathVariable Long clientId) {
        this.clientService.deleteClient(clientId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> getClient(@NonNull @PathVariable Long clientId) {
        return ResponseEntity.ok(this.clientService.getClient(clientId));
    }

    @PutMapping("/")
    public ResponseEntity<Client> updateClient(@NonNull @RequestBody Client client) {
        this.clientService.updateClient(client);
        return ResponseEntity.ok(client);
    }
}
