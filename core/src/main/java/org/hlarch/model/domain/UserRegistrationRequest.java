package org.hlarch.model.domain;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder(builderClassName = "Builder")
public class UserRegistrationRequest {

    @NonNull
    String username;

    @NonNull
    String password;

    @NonNull
    String name;

    @NonNull
    String lastName;

    @NonNull
    LocalDate dateOfBirth;

    @NonNull
    Gender gender;

    @NonNull
    String hobbies;

    @NonNull
    String city;
}
