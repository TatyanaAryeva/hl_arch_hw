package org.hlarch.model.data;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
public class UserDm {

    private int id;
    private String username;
    private String password;
    private String name;
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private String hobbies;
    private String city;
}
