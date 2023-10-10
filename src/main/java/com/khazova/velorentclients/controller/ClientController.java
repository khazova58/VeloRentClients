package com.khazova.velorentclients.controller;

import com.khazova.velorentclients.model.ClientDto;
import com.khazova.velorentclients.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @PostMapping
    @Operation(summary = "Новый клиент")
    public ClientDto newClient(@RequestBody ClientDto clientByRequest) {
        return service.newClient(clientByRequest);
    }

    @GetMapping
    @Operation(summary = "Получить клиента по id")
    public ClientDto getClient(@RequestParam String id) {
        return service.getClientById(id);
    }

    @GetMapping("/all")
    @Operation(summary = "Получить всех клиентов")
    public List<ClientDto> getAllClients() {
        return service.getAllClients();
    }

    @PutMapping
    @Operation(summary = "Редактировать данные клиента")
    public ClientDto updateClient(@RequestParam String id, @RequestBody ClientDto updateInfoClient){
        return service.updateClientByRequest(id, updateInfoClient);
    }

}
