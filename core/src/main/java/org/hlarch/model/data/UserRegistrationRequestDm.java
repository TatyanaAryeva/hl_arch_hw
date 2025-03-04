package org.hlarch.model.data;

import lombok.Data;

@Data
public class UserRegistrationRequestDm {

    private String username;
    private String encryptedPassword;
    private String name;
    private String lastName;
    private String dateOfBirth;
    private String gender;
    String hobbies;
    private String city;
}
