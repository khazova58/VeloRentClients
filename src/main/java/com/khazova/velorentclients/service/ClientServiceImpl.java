package com.khazova.velorentclients.service;

import com.khazova.velorentclients.exceptions.BusinessError;
import com.khazova.velorentclients.exceptions.ServiceException;
import com.khazova.velorentclients.mapper.ClientMapper;
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
    private final ClientMapper clientMapper;

    public ClientServiceImpl(ClientRepository repository, ClientMapper clientMapper) {
        this.repository = repository;
        this.clientMapper = clientMapper;
    }

    @Override
    @Transactional
    public ClientDto newClient(ClientDto clientByRequest) {
        Client save = repository.save(clientMapper.mapToEntity(clientByRequest));
        return clientMapper.entityToDto(save);
    }

    @Override
    public ClientDto getClientById(String id) {
        return getClient(id);
    }

    @Override
    public List<ClientDto> getAllClients(Pageable pageable) {
        Page<Client> clients = repository.findAll(pageable);
        return clients.stream()
                .map(clientMapper::entityToDto)
                .toList();
    }

    @Override
    @Transactional
    public ClientDto updateClientByRequest(String id, ClientDto updateInfoClient) {
        Client foundClient = repository.findById(id).orElseThrow(() -> new ServiceException(BusinessError.CLIENT_NOT_FOUND, id));
        Client request = clientMapper.updateClientByRequest(updateInfoClient, foundClient);
        repository.save(request);
        return clientMapper.entityToDto(request);
    }

    @Override
    @Transactional
    public void deleteClientByRequest(String id) {
        Client foundClient = repository.findById(id).orElseThrow(() -> new ServiceException(BusinessError.CLIENT_NOT_FOUND, id));
        repository.deleteById(foundClient.getId());
    }

    @Override
    public List<ClientDto> findClientsBySurname(String surname) {
        List<Client> foundClients = repository.findAllByName(surname);
        return foundClients.stream()
                .map(clientMapper::entityToDto)
                .toList();
    }

    private ClientDto getClient(String id) {
        Client foundClient = repository.findById(id).orElseThrow(() -> new ServiceException(BusinessError.CLIENT_NOT_FOUND, id));
        return clientMapper.entityToDto(foundClient);
    }
}
