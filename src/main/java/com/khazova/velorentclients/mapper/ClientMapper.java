package com.khazova.velorentclients.mapper;

import com.khazova.velorentclients.model.Client;
import com.khazova.velorentclients.model.dto.ClientDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface ClientMapper {

    @Mapping(target = "source", source = "source.id")
    @Mapping(target = "referrer", source = "referrer.id")
    ClientDto entityToDto(Client entity);

    @Mapping(target = "source", ignore = true)
    @Mapping(target = "referrer.id", source = "referrer")
    Client updateClientByRequest(ClientDto updateInfoClient, @MappingTarget Client foundClient);
}