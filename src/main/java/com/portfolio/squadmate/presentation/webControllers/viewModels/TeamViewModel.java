package com.portfolio.squadmate.presentation.webControllers.viewModels;

import com.portfolio.squadmate.domain.TeamMatch;

public record TeamViewModel(String name, int goals, CoachViewModel coach) {

    public static TeamViewModel from (final TeamMatch teamMatch){
        return new TeamViewModel(teamMatch.getTeam().getName(), teamMatch.getGoals(), CoachViewModel.from(teamMatch.getTeam().getCoach()));
    }
}
