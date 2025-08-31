package com.portfolio.squadmate.presentation.webApi.dto;

import com.portfolio.squadmate.domain.Match;
import java.time.LocalDateTime;


//I use the teamName of the TeamMatch entity here because some teams may not be registered yet in the system
//coaches can create matches against teams that are not currently in the database
public record MatchDTO(Integer matchId, LocalDateTime matchDate, String team1, String team2) {
    public static MatchDTO from(final Match match){

        return new MatchDTO(match.getId(), match.getMatchDate(), match.getMatchStats().getFirst().getTeamName(),
                match.getMatchStats().getLast().getTeamName());
    }
}
