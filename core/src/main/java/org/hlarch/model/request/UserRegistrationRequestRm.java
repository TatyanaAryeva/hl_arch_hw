package org.hlarch.model.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hlarch.model.domain.Gender;
import org.hlarch.validation.ValueOf;

@Data
public class UserRegistrationRequestRm {

    @Pattern(regexp = "^[A-Za-z_0-9]{3,20}$")
    private String username;

    @Pattern(regexp = "^(?=.*[A-Za-z])[A-Za-z\\d!@#$%&*\\-+]{6,12}$")
    private String password;

    @Pattern(regexp="^[A-Za-zА-Яа-яЁё]{2,50}$")
    private String name;

    @Pattern(regexp="^[A-Za-zА-Яа-яЁё]{2,50}$")
    private String lastName;

    @Pattern(regexp = "^(0[1-9]|1[012]).(0[1-9]|[12][0-9]|3[01]).(19|20)\\d\\d$")
    private String dateOfBirth;

    @ValueOf(enumClass = Gender.class)
    private String gender;

    @Pattern(regexp = "^[A-Za-zА-Яа-яЁё0-9\\s\\p{Punct}]{0,125}$")
    private String hobbies;

    @Pattern(regexp = "^[A-Za-zА-Яа-яЁё\\s-]{2,40}$")
    private String city;

}