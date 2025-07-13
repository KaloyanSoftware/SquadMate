package com.portfolio.squadmate.presentation.webControllers.viewModels;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class NewUserViewModel{
    @NotEmpty(message="Email is mandatory!")
    @Email (message = "Should respect email format with '@'!")
    private String email;

    @NotEmpty(message = "Password is mandatory!")
    private String password;

    @NotEmpty(message="First name is mandatory!")
    @Size(min=2, max=15, message = "First name should be between 2 and 15 characters!")
    private String firstName;

    @NotEmpty(message = "Last name is mandatory!")
    @Size(min=3, max=20, message = "Last name should be between 3 and 20 characters!")
    private String lastName;

    @NotNull(message = "Birth date is mandatory!")
    @Past (message = "Birth date should be in the past!")
    private LocalDate birthDate;

    @NotEmpty(message = "Choosing a role is mandatory!")
    @Pattern(regexp = "Player|Coach", message = "Role must be either 'Player' or 'Coach'!")
    private String role;
}


