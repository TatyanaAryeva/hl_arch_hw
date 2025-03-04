package org.hlarch.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CredentialsRm {

    @Pattern(regexp = "^[A-Za-z_0-9]+$")
    @Size(min = 3, max = 20)
    private String username;

    @Pattern(regexp = "^(?=.*[A-Za-z])[A-Za-z\\d!@#$%&*\\-+]{6,50}$")
    @Size(min = 6, max = 12)
    private String password;
}
