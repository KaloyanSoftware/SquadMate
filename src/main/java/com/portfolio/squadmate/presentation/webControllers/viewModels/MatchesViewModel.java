package com.portfolio.squadmate.presentation.webControllers.viewModels;

import com.portfolio.squadmate.domain.Match;
import java.util.List;

public record MatchesViewModel(List<MatchViewModel> matchViewModels) {

    public static MatchesViewModel from(List<Match> matchEvents) {
        return new MatchesViewModel(matchEvents.stream()
                .map(MatchViewModel::from).toList());
    }
}
