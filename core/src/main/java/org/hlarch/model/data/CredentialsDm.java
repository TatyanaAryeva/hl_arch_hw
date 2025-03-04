package org.hlarch.model.data;

import lombok.Data;

@Data
public class CredentialsDm {

    private String username;
    private String encryptedPassword;
    private String token;
}
