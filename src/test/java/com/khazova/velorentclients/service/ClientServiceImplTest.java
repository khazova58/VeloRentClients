package com.khazova.velorentclients.service;

import com.khazova.velorentclients.mapper.ClientMapperImpl;
import com.khazova.velorentclients.model.entity.Client;
import com.khazova.velorentclients.model.entity.Source;
import com.khazova.velorentclients.model.dto.ClientDto;
import com.khazova.velorentclients.repo.ClientRepository;
import com.khazova.velorentclients.repo.SourceRepository;
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

    @Mock
    private SourceRepository sourceRepository;

    private ClientMapperImpl mapper = new ClientMapperImpl();

    private Pageable pageable;

    private ClientServiceImpl sut;

    private final ClientDto dto = new ClientDto("Соколова",
            "Светлана", "Сергеевна", 36, "sveta@ya.ru",
            "89273661328", "Москва", 1, "testReferrer");

    @BeforeEach
    void setUp() {
        sut = new ClientServiceImpl(repository, sourceRepository, mapper);
    }

    @Test
    @DisplayName("Создание нового клиента")
    void newClient_positiveResult() {
        Client client = createClient();
        Source source = new Source(1, "web", "Пришел из web", null);
        Client referrer = createReferrer();

        Mockito.when(sourceRepository.findById(1)).thenReturn(Optional.of(source));
        Mockito.when(repository.findById("testReferrer")).thenReturn(Optional.ofNullable(referrer));
        Mockito.when(repository.save(any())).thenReturn(client);

        ClientDto clientDto = sut.newClient(dto);
        assertEquals(client.getFirstName(), clientDto.getFirstName());
    }

    @Test
    @DisplayName("Поиск клиента по id (клиент есть в базе)")
    void getClientById_positiveResult() {
        Client client = createClient();
        Mockito.when(repository.findById(any())).thenReturn(Optional.of(client));
        ClientDto foundClient = sut.getClientById("testId");
        assertEquals("Светлана", foundClient.getFirstName());
    }

    @Test
    @DisplayName("Вывод всех клиентов")
    void getAllClients_expectedTwo() {
        Client client = createClient();
        List<Client> clients = List.of(client, client);
        Page<Client> page = new PageImpl<>(clients);
        Mockito.when(repository.findAll(pageable)).thenReturn(page);
        List<ClientDto> foundClients = sut.getAllClients(pageable);
        assertEquals(2, foundClients.size());
    }

    @Test
    @DisplayName("Обновление клиента по id")
    void updateClientByRequest_positiveResult() {
        Client client = createClient();
        Mockito.when(repository.findById("testId")).thenReturn(Optional.of(client));
        sut.updateClientByRequest("testId", dto);
        Mockito.verify(repository).save(client);
    }

    @Test
    @DisplayName("Удаление клиента из базы (клиент есть в базе)")
    void deleteClientByRequest_positiveResult() {
        Client client = createClient();
        client.setId("testId");
        Mockito.when(repository.findById("testId")).thenReturn(Optional.of(client));
        sut.deleteClientByRequest("testId");
        Mockito.verify(repository).deleteById("testId");
    }

    @Test
    @DisplayName("Найти клиента по имени")
    void findClientsBySurname_expectedOneClient() {
        Client client = createClient();
        List<Client> found = List.of(client, client);
        Mockito.when(repository.findAllByName("Светлана")).thenReturn(found);
        List<ClientDto> byRequest = sut.findClientsByLastName("Светлана");
        assertEquals(found.size(), byRequest.size());
    }

    /**
     * Создание тестового клиента
     *
     * @return подготовленный клиент
     */
    Client createClient() {
        return Client.builder()
                .firstName("Светлана")
                .build();
    }

    /**
     * Создание тестового referrer
     *
     * @return подготовленный referrer
     */
    Client createReferrer() {
        return Client.builder()
                .id("testReferrer")
                .build();
    }
}