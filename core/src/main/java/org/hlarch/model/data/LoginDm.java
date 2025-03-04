package org.hlarch.model.data;

import lombok.Data;

@Data
public class LoginDm {

    private final String username;
    private final String cypheredPassword;
    private final String token;
}
