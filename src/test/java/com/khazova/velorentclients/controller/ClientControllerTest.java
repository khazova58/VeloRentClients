package com.khazova.velorentclients.controller;

import com.khazova.velorentclients.model.ClientDto;
import com.khazova.velorentclients.service.ClientService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
class ClientControllerTest {

    @MockBean
    private ClientService service;

    @Autowired
    private MockMvc mockMvc;

    private final ClientDto dto = new ClientDto("Sokolova", "Svetlana", "Sergeevna", 36, "sveta@ya.ru", "89273661328", "Moscow");


    @Test
    @DisplayName("Создание нового клиента")
    void newClient() throws Exception {
        Mockito.when(service.newClient(any())).thenReturn(dto);

        mockMvc.perform(post("http://localhost:8081/api/v1/client")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                  "surname": "Sokolova",
                                  "name": "Svetlana",
                                  "lastname": "Sergeevna",
                                  "age": 36,
                                  "email": "sveta@ya.ru",
                                  "phoneNumber": "89273661328",
                                  "address": "Moscow"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value(dto.getSurname()));
    }

    @Test
    @DisplayName("Получить клиента по id")
    void getClient() throws Exception {
        Mockito.when(service.getClientById("testId")).thenReturn(dto);

        mockMvc.perform(get("http://localhost:8081/api/v1/client")
                        .param("id", "testId"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.surname").value(dto.getSurname()));
    }

    @Test
    @DisplayName("Получить всех клиентов")
    void getAllClients() throws Exception {
        Mockito.when(service.getAllClients(any())).thenReturn(List.of(dto));

        String expected = "[{\"surname\":\"Sokolova\",\"name\":\"Svetlana\",\"lastname\":\"Sergeevna\",\"age\":36,\"email\":\"sveta@ya.ru\",\"phoneNumber\":\"89273661328\",\"address\":\"Moscow\"}]";

        MvcResult result = mockMvc.perform(get("http://localhost:8081/api/v1/client/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(expected, result.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("Получить клиентов по фамилии")
    void getClientsBySurname() throws Exception {
        Mockito.when(service.findClientsBySurname("Sokolova")).thenReturn(List.of(dto));
        String expected = "[{\"surname\":\"Sokolova\",\"name\":\"Svetlana\",\"lastname\":\"Sergeevna\",\"age\":36,\"email\":\"sveta@ya.ru\",\"phoneNumber\":\"89273661328\",\"address\":\"Moscow\"}]";

        MvcResult result = mockMvc.perform(get("http://localhost:8081/api/v1/client/byName")
                        .param("surname", "Sokolova"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(expected, result.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("Обновить клиента по id")
    void updateClient() throws Exception {
        Mockito.when(service.updateClientByRequest("testId", dto)).thenReturn(dto);

        this.mockMvc.perform(put("http://localhost:8081/api/v1/client")
                        .param("id", "testId")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                  "surname": "Sokolova",
                                  "name": "Svetlana",
                                  "lastname": "Sergeevna",
                                  "age": 36,
                                  "email": "sveta@ya.ru",
                                  "phoneNumber": "89273661328",
                                  "address": "Moscow"
                                }
                                """))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("surname").value("Sokolova"));
    }

    @Test
    @DisplayName("Удаление клиента по id")
    void deleteClient() throws Exception {
        mockMvc.perform(delete("http://localhost:8081/api/v1/client")
                        .param("id", "testId"))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}