package org.hlarch.model.domain;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.Date;

@Value
@Builder(builderClassName = "Builder")
public class User {

    int id;

    @NonNull
    Credentials credentials;

    @NonNull
    String name;

    @NonNull
    String lastName;

    @NonNull
    Date dateOfBirth;

    @NonNull
    Gender gender;

    @NonNull
    String hobbies;

    @NonNull
    String city;
}
