package com.portfolio.squadmate.presentation.webControllers.viewModels;

import com.portfolio.squadmate.domain.Team;

public record TeamViewModel(String name, CoachViewModel coach) {

    public static TeamViewModel from (final Team team){
        return new TeamViewModel(team.getName(), CoachViewModel.from(team.getCoach()));
    }
}
