package com.portfolio.squadmate.presentation.webControllers.viewModels;

import java.time.LocalDate;

public record NewUserViewModel (String email,
                                String password,
                                String firstName,
                                String lastName,
                                LocalDate birthDate,
                                String role) {
}
