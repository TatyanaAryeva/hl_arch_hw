package org.hlarch.model.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class Credentials {

    String username;
    String password;
}
