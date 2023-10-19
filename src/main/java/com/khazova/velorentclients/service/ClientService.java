package com.khazova.velorentclients.service;

import com.khazova.velorentclients.model.ClientDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientService {

    ClientDto newClient(ClientDto clientByRequest);

    ClientDto getClientById(String id);

    List<ClientDto> getAllClients(Pageable pageable);

    ClientDto updateClientByRequest(String id, ClientDto updateInfoClient);

    void deleteClientByRequest(String id);

    List<ClientDto> findClientsBySurname(String surname);
}
