package com.khazova.velorentclients.controller;

import com.khazova.velorentclients.model.ClientDto;
import com.khazova.velorentclients.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/client")
@Tag(name = "Клиенты")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    /**
     * Добавить нового клиента
     *
     * @param clientByRequest информация о клиенте (фамилия, имя, отчество, возраст, телефон, электронная почта)
     * @return созданный клиент
     */
    @PostMapping
    @Operation(summary = "Новый клиент")
    public ClientDto newClient(@Valid @RequestBody ClientDto clientByRequest) {
        return service.newClient(clientByRequest);
    }

    /**
     * Найти клиента в базе по идентификатору
     *
     * @param id - идентификатор
     * @return найденый клиент или сообщение об ошибке в случае отсутствия в базе
     */
    @GetMapping
    @Operation(summary = "Получить клиента по id")
    public ClientDto getClient(@Valid @RequestParam String id) {
        return service.getClientById(id);
    }

    /**
     * Получить список всех клиентов организации с возможностью сортировки по фамилии
     *
     * @param pageable объект сортировки
     * @return список клиентов
     */
    @GetMapping("/all")
    @Operation(summary = "Получить всех клиентов")
    public List<ClientDto> getAllClients(@ParameterObject @PageableDefault(sort = "surname", direction = Sort.Direction.ASC) Pageable pageable) {
        return service.getAllClients(pageable);
    }

    /**
     * Получить клиента по фамилии
     *
     * @param surname фамилия клиента
     * @return найденый клиент или сообщение об ошибке в случае отсутствия в базе
     */
    @GetMapping("/byName")
    @Operation(summary = "Получить клиентов по фамилии")
    public List<ClientDto> getClientsBySurname(@RequestParam String surname) {
        return service.findClientsBySurname(surname);
    }

    /**
     * Возможность редактировать данные клиента
     *
     * @param id               идентификатор клиента
     * @param updateInfoClient новая информация по клиенту
     * @return обновленная запись о клиенте или сообщение об ошибке в случае его отсутствия в базе
     */
    @PutMapping
    @Operation(summary = "Редактировать данные клиента")
    public ClientDto updateClient(@RequestParam String id, @Valid @RequestBody ClientDto updateInfoClient) {
        return service.updateClientByRequest(id, updateInfoClient);
    }

    /**
     * Удаление клиента из базы данных организации
     *
     * @param id идентификатор клиента
     * @return сообщение об удалении клиента с идентификаторм или об ошибке в случае отсутствия в базе
     */
    @DeleteMapping
    @Operation(summary = "Удалить клиента по id")
    public String deleteClient(@RequestParam String id) {
        return service.deleteClientByRequest(id);
    }
}
