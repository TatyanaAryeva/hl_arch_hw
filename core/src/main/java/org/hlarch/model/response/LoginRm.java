package org.hlarch.model.response;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class LoginRm {

    @NonNull
    UUID token;

    @NonNull
    String message;
}
