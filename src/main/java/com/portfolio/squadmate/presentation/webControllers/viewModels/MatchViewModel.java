package com.portfolio.squadmate.presentation.webControllers.viewModels;

import com.portfolio.squadmate.domain.Match;
import java.time.LocalDateTime;

public record MatchViewModel(LocalDateTime matchDate, TeamViewModel firstTeam, TeamViewModel secondTeam,
                             String location) {

    public static MatchViewModel from(final Match match) {
        return new MatchViewModel(match.getMatchDate(), TeamViewModel.from(match.getMatchEvents().getFirst().getTeam())
                , TeamViewModel.from(match.getMatchEvents().getLast().getTeam()), match.getLocation());
    }
}
