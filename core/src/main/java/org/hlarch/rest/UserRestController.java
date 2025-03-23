package org.hlarch.rest;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.hlarch.mapper.Domain2ResponseMapper;
import org.hlarch.mapper.Request2DomainMapper;
import org.hlarch.model.domain.Credentials;
import org.hlarch.model.domain.Login;
import org.hlarch.model.domain.User;
import org.hlarch.model.domain.UserRegistrationRequest;
import org.hlarch.model.request.CredentialsRm;
import org.hlarch.model.request.UserRegistrationRequestRm;
import org.hlarch.model.response.LoginRm;
import org.hlarch.model.response.UserRm;
import org.hlarch.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {

    private final Request2DomainMapper request2DomainMapper;
    private final Domain2ResponseMapper domain2ResponseMapper;
    private final UserService userService;

    public UserRestController(Request2DomainMapper request2DomainMapper, Domain2ResponseMapper domain2ResponseMapper, UserService userService) {
        this.request2DomainMapper = request2DomainMapper;
        this.domain2ResponseMapper = domain2ResponseMapper;
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public int register(@Valid @RequestBody UserRegistrationRequestRm registrationRequestRm) {
        UserRegistrationRequest request = request2DomainMapper.map(registrationRequestRm);
        return userService.register(request);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginRm login(@Valid @RequestBody CredentialsRm credentialsRm) {
        Credentials credentials = request2DomainMapper.map(credentialsRm);
        Login login = userService.login(credentials);
        return request2DomainMapper.map(login);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserRm get(@PathVariable("id") int id) {
        User user = userService.get(id);
        return domain2ResponseMapper.map(user);
    }

}
