package com.portfolio.squadmate.presentation.webControllers.viewModels;

import com.portfolio.squadmate.domain.TeamMatch;

public record TeamViewModel(String name, int goals) {

    public static TeamViewModel from (final TeamMatch teamMatch){

        return new TeamViewModel(teamMatch.getTeamName(), teamMatch.getGoals());
    }
}
