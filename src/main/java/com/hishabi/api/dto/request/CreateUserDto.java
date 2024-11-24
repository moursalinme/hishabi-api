package com.hishabi.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserDto {

    @NotBlank(message = "You must provide a first name.")
    @Size(min = 3, max = 20, message = "First name must be between 3 to 20 chars")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must contain only letters.")
    private String firstName;

    @NotBlank(message = "You must provide a last name.")
    @Size(min = 3, max = 20, message = "Last name must be between 3 to 20 chars")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must contain only letters.")
    private String lastName;

    @NotBlank(message = "You must provide an email.")
    @Email(message = "You must provide a valid email.")
    private String email;

    @NotBlank(message = "You must provide a password.")
    @Size(min = 6, message = "Password must be at least 6 chars.")
    private String password;

    private String role;

}
