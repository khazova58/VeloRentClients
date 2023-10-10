package com.khazova.velorentclients.mapper;

import com.khazova.velorentclients.model.Client;
import com.khazova.velorentclients.model.ClientDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface Map {

    Client mapToEntity(ClientDto clientByRequest);

    ClientDto entityToDto(Client entity);

    Client updateClientByRequest(ClientDto updateInfoClient, @MappingTarget Client foundClient);
}
