package com.hishabi.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequestDto {

    @NotBlank(message = "You must provide an email.")
    @Email(message = "You must provide a valid email.")
    private String email;

    @NotBlank(message = "You must provide a password.")
    @Size(min = 6, message = "Password must be at least 6 chars.")
    private String password;
}
