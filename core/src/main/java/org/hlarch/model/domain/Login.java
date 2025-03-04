package org.hlarch.model.domain;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class Login {

    @NonNull
    UUID token;

    @NonNull
    String message;
}
