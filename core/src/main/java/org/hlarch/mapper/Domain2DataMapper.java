package org.hlarch.mapper;

import org.hlarch.model.data.CredentialsDm;
import org.hlarch.model.data.UserRegistrationRequestDm;
import org.hlarch.model.domain.Credentials;
import org.hlarch.model.domain.UserRegistrationRequest;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.Base64;


@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface Domain2DataMapper {

    @Mapping(target = "encryptedPassword", expression = "java(java.util.Base64.getDecoder().decode(request.getPassword()).toString())")
    UserRegistrationRequestDm map(UserRegistrationRequest request);

    @Mapping(target = "encryptedPassword", expression = "java(java.util.Base64.getDecoder().decode(request.getPassword()).toString())")
    CredentialsDm map(Credentials request);

}
