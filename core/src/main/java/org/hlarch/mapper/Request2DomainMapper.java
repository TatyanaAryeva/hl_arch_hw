package org.hlarch.mapper;

import org.hlarch.model.domain.Credentials;
import org.hlarch.model.domain.Login;
import org.hlarch.model.domain.UserRegistrationRequest;
import org.hlarch.model.request.CredentialsRm;
import org.hlarch.model.request.UserRegistrationRequestRm;
import org.hlarch.model.response.LoginRm;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;


@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface Request2DomainMapper {

    @Mapping(target = "dateOfBirth", expression = "java(mapDateOfBirth(requestRm.getDateOfBirth()))")
    UserRegistrationRequest map(UserRegistrationRequestRm requestRm);


    Credentials map(CredentialsRm requestRm);

    LoginRm map(Login login);

    default LocalDate mapDateOfBirth(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
