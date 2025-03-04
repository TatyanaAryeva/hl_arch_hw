package org.hlarch.model.response;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.hlarch.model.domain.Gender;

import java.util.Date;

@Value
@Builder(builderClassName = "Builder")
public class UserRm {

    int id;

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
