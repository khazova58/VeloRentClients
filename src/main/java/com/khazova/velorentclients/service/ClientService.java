package com.khazova.velorentclients.service;

import com.khazova.velorentclients.model.ClientDto;

import java.util.List;

public interface ClientService {

    ClientDto newClient(ClientDto clientByRequest);

    ClientDto getClientById(String id);

    List<ClientDto> getAllClients();

    ClientDto updateClientByRequest(String id, ClientDto updateInfoClient);
}
