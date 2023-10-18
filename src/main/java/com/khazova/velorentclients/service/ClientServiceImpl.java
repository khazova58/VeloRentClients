package com.khazova.velorentclients.service;

import com.khazova.velorentclients.exceptions.BusinessError;
import com.khazova.velorentclients.exceptions.ServiceException;
import com.khazova.velorentclients.mapper.Map;
import com.khazova.velorentclients.model.Client;
import com.khazova.velorentclients.model.ClientDto;
import com.khazova.velorentclients.repo.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;
    private final Map map;

    public ClientServiceImpl(ClientRepository repository, Map map) {
        this.repository = repository;
        this.map = map;
    }

    @Override
    @Transactional
    public ClientDto newClient(ClientDto clientByRequest) {
        Client save = repository.save(map.mapToEntity(clientByRequest));
        return map.entityToDto(save);
    }

    @Override
    public ClientDto getClientById(String id) {
        return getClient(id);
    }

    @Override
    public List<ClientDto> getAllClients(Pageable pageable) {
        Page<Client> clients = repository.findAll(pageable);
        return clients.stream()
                .map(map::entityToDto)
                .toList();
    }

    @Override
    @Transactional
    public ClientDto updateClientByRequest(String id, ClientDto updateInfoClient) {
        Client foundClient = repository.findById(id).orElseThrow(() -> new ServiceException(BusinessError.CLIENT_NOT_FOUND, id));
        Client request = map.updateClientByRequest(updateInfoClient, foundClient);
        repository.save(request);
        return map.entityToDto(request);
    }

    @Override
    @Transactional
    public String deleteClientByRequest(String id) {
        String message = "Клиент c id:%s удален из базы";
        Client foundClient = repository.findById(id).orElseThrow(() -> new ServiceException(BusinessError.CLIENT_NOT_FOUND, id));
        repository.deleteById(foundClient.getId());
        return message.formatted(id);
    }

    @Override
    public List<ClientDto> findClientsBySurname(String surname) {
        List<Client> foundClients = repository.findAllByName(surname);
        return foundClients.stream()
                .map(map::entityToDto)
                .toList();
    }

    private ClientDto getClient(String id) {
        Client foundClient = repository.findById(id).orElseThrow(() -> new ServiceException(BusinessError.CLIENT_NOT_FOUND, id));
        return map.entityToDto(foundClient);
    }
}
