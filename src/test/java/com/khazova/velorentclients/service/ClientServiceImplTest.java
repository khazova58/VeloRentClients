package com.khazova.velorentclients.service;

import com.khazova.velorentclients.mapper.MapImpl;
import com.khazova.velorentclients.model.Client;
import com.khazova.velorentclients.model.ClientDto;
import com.khazova.velorentclients.repo.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    @Mock
    private ClientRepository repository;
    private MapImpl mapper = new MapImpl();

    private Pageable pageable;

    private ClientServiceImpl sut;

    private final Client client = new Client("Соколова", "Светлана", "Сергеевна", 36, "sveta@ya.ru", "89273661328");
    private final ClientDto dto = new ClientDto("Соколова", "Светлана", "Сергеевна", 36, "sveta@ya.ru", "89273661328");


    @BeforeEach
    void setUp() {
        sut = new ClientServiceImpl(repository, mapper);
    }

    @Test
    @DisplayName("Создание нового клиента")
    void newClient_positiveResult() {
        Mockito.when(repository.save(any())).thenReturn(client);
        ClientDto clientDto = sut.newClient(dto);
        assertEquals(client.getName(), clientDto.getName());
    }

    @Test
    @DisplayName("Поиск клиента по id (клиент есть в базе)")
    void getClientById_positiveResult() {
        Mockito.when(repository.findById(any())).thenReturn(Optional.of(client));
        ClientDto foundClient = sut.getClientById("testId");
        assertEquals("Светлана", foundClient.getName());
    }

    @Test
    @DisplayName("Вывод всех клиентов")
    void getAllClients_expectedTwo() {
        List<Client> clients = List.of(client, client);
        Page<Client> page = new PageImpl<>(clients);
        Mockito.when(repository.findAll(pageable)).thenReturn(page);
        List<ClientDto> foundClients = sut.getAllClients(pageable);
        assertEquals(2, foundClients.size());
    }

    @Test
    @DisplayName("Обновление клиента по id")
    void updateClientByRequest_positiveResult() {
        Mockito.when(repository.findById("testId")).thenReturn(Optional.of(client));
        sut.updateClientByRequest("testId", dto);
        Mockito.verify(repository).save(client);
    }

    @Test
    @DisplayName("Удаление клиента из базы (клиент есть в базе)")
    void deleteClientByRequest_positiveResult() {
        client.setId("testId");
        Mockito.when(repository.findById("testId")).thenReturn(Optional.of(client));
        sut.deleteClientByRequest("testId");
        Mockito.verify(repository).deleteById("testId");
    }

    @Test
    @DisplayName("Найти клиента по имени")
    void findClientsBySurname_expectedOneClient() {
        List<Client> found = List.of(client, client);
        Mockito.when(repository.findAllByName("Светлана")).thenReturn(found);
        List<ClientDto> byRequest = sut.findClientsBySurname("Светлана");
        assertEquals(found.size(), byRequest.size());
    }
}