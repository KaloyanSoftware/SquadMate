package com.portfolio.squadmate.presentation.webApi.dto;

import com.portfolio.squadmate.domain.Match;
import java.time.LocalDateTime;

public record MatchDTO(Integer matchId, LocalDateTime matchDate, TeamDTO team1, TeamDTO team2) {
    public static MatchDTO from(final Match match){
        return new MatchDTO(match.getId(), match.getMatchDate(), TeamDTO.from(match.getMatchStats().getFirst().getTeam()),
                TeamDTO.from(match.getMatchStats().getLast().getTeam()));
    }
}
