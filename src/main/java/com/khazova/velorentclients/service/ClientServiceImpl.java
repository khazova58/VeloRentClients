package com.khazova.velorentclients.service;

import com.khazova.velorentclients.exceptions.BusinessError;
import com.khazova.velorentclients.exceptions.ServiceException;
import com.khazova.velorentclients.mapper.ClientMapper;
import com.khazova.velorentclients.model.entity.Client;
import com.khazova.velorentclients.model.entity.Source;
import com.khazova.velorentclients.model.dto.ClientDto;
import com.khazova.velorentclients.repo.ClientRepository;
import com.khazova.velorentclients.repo.SourceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;

    private final SourceRepository sourceRepository;

    private final ClientMapper clientMapper;

    public ClientServiceImpl(ClientRepository repository, SourceRepository sourceRepository, ClientMapper clientMapper) {
        this.repository = repository;
        this.sourceRepository = sourceRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    @Transactional
    public ClientDto newClient(ClientDto clientByRequest) {
        Optional<Client> byEmail = repository.findByEmail(clientByRequest.getEmail());
        if (byEmail.isPresent()) throw new ServiceException(BusinessError.EMAIL_IS_PRESENT, clientByRequest.getEmail());

        Source sourceByRequest = sourceRepository.findById(clientByRequest.getSource())
                .orElseThrow(() -> new ServiceException(BusinessError.SOURCE_NOT_FOUND));

        Client newClient = Client.builder()
                .lastName(clientByRequest.getLastName())
                .firstName(clientByRequest.getFirstName())
                .middleName(clientByRequest.getMiddleName())
                .age(clientByRequest.getAge())
                .address(clientByRequest.getAddress())
                .phoneNumber(clientByRequest.getPhoneNumber())
                .email(clientByRequest.getEmail())
                .source(sourceByRequest)
                .referrer(getReferrer(clientByRequest))
                .build();

        Client save = repository.save(newClient);
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
    public List<ClientDto> findClientsByLastName(String lastName) {
        List<Client> foundClients = repository.findAllByName(lastName);
        if (foundClients.isEmpty()) throw new ServiceException(BusinessError.NOTHING_ALL, lastName);
        return foundClients.stream()
                .map(clientMapper::entityToDto)
                .toList();
    }

    private ClientDto getClient(String id) {
        Client foundClient = repository.findById(id).orElseThrow(() -> new ServiceException(BusinessError.CLIENT_NOT_FOUND, id));
        return clientMapper.entityToDto(foundClient);
    }

    private Client getReferrer(ClientDto clientByRequest) {
        if (clientByRequest.getReferrer() == null || clientByRequest.getReferrer().isEmpty()) {
            return null;
        } else {
            return repository.findById(clientByRequest.getReferrer())
                    .orElseThrow(() -> new ServiceException(BusinessError.CLIENT_NOT_FOUND, clientByRequest.getReferrer()));
        }
    }
}
