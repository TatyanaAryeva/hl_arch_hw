package org.hlarch.mapper;

import lombok.extern.slf4j.Slf4j;
import org.hlarch.model.data.UserDm;
import org.hlarch.model.domain.Credentials;
import org.hlarch.model.domain.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.Base64;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface Data2DomainMapper {

    @Mapping(target = "credentials", expression = "java(org.hlarch.model.domain.Credentials.builder().password(userDm.getPassword()).username(userDm.getUsername()).build())")
    User map(UserDm userDm);



}
