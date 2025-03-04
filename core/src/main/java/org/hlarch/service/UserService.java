package org.hlarch.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hlarch.dao.UserDao;
import org.hlarch.mapper.Data2DomainMapper;
import org.hlarch.mapper.Domain2DataMapper;
import org.hlarch.model.data.CredentialsDm;
import org.hlarch.model.data.UserDm;
import org.hlarch.model.data.UserRegistrationRequestDm;
import org.hlarch.model.domain.Credentials;
import org.hlarch.model.domain.Login;
import org.hlarch.model.domain.User;
import org.hlarch.model.domain.UserRegistrationRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;
    private final Domain2DataMapper domain2DataMapper;
    private final Data2DomainMapper data2DomainMapper;

    public int register(UserRegistrationRequest request) {
        boolean isUserExists = userDao.isUserExists(request.getUsername());
        if (isUserExists) {
            log.error(String.format("User with username %s can not be registered. This user already exists", request.getUsername()));
            return -1;
        }
        UserRegistrationRequestDm registrationRequestDm = domain2DataMapper.map(request);
        int res = userDao.register(registrationRequestDm);
        return res;
    }

    public User get(int id) {
        List<UserDm> userDm = userDao.get(id);
        log.info("userDm: "+ userDm.get(0));
        return data2DomainMapper.map(userDm.get(0));
    }

    public Login login(Credentials credentials) {
        CredentialsDm credentialsDm = domain2DataMapper.map(credentials);

        boolean isValidCredentials = userDao.isValidCredentials(credentialsDm);
        if (!isValidCredentials) {
            log.error(String.format("Invalid username or password"));
        }

        UUID token = UUID.randomUUID();
        credentialsDm.setToken(token.toString());

        boolean isSuccess = userDao.login(credentialsDm);
        if (!isSuccess) {
            log.info(String.format("Login unsuccessful"));
        }

        return Login.builder()
                .token(token)
                .message("You are logged in.")
                .build();
    }
}
