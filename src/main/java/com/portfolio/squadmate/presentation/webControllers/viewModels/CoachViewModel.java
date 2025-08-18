package com.portfolio.squadmate.presentation.webControllers.viewModels;

import com.portfolio.squadmate.domain.Coach;

public record CoachViewModel(String firstName, String lastName) {

    public static CoachViewModel from(final Coach coach){
        return new CoachViewModel(coach.getFirstName(), coach.getLastName());
    }
}
