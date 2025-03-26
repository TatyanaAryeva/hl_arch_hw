package org.hlarch.mapper;

import org.hlarch.model.domain.User;
import org.hlarch.model.response.UserRm;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface Domain2ResponseMapper {

    UserRm map(User user);

    List<UserRm> map(List<User> users);
}
