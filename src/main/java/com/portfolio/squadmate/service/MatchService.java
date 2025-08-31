package com.portfolio.squadmate.service;

import com.portfolio.squadmate.domain.Coach;
import com.portfolio.squadmate.domain.Match;
import com.portfolio.squadmate.domain.Team;
import com.portfolio.squadmate.domain.TeamMatch;
import com.portfolio.squadmate.repository.CoachRepository;
import com.portfolio.squadmate.repository.MatchRepository;
import com.portfolio.squadmate.repository.TeamRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MatchService {

    private final MatchRepository matchRepository;

    private final CoachRepository coachRepository;

    private final TeamRepository teamRepository;

    public MatchService(final MatchRepository matchRepository, final CoachRepository coachRepository,
                        final TeamRepository teamRepository) {
        this.matchRepository = matchRepository;
        this.coachRepository = coachRepository;
        this.teamRepository = teamRepository;
    }

    public Match createMatch(final Integer id, final LocalDateTime matchDate,
                             final String location,
                             final String opponentTeam){

        final Coach coach  = coachRepository.getCoachWithTeam(id).orElseThrow();

        final Team team = coach.getTeam();

        final Match match = new Match();

        match.setMatchDate(matchDate);
        match.setLocation(location);

        // First TeamMatch: the coach's team
        final TeamMatch coachTeamMatch = new TeamMatch();
        coachTeamMatch.setTeam(team);
        coachTeamMatch.setMatch(match);

        // Second TeamMatch: the opponent
        final TeamMatch opponentTeamMatch = new TeamMatch();
        opponentTeamMatch.setMatch(match);

        // Check if opponent exists in DB
        teamRepository.findTeamByName(opponentTeam)
                .ifPresentOrElse(
                        opponentTeamMatch::setTeam,
                        () -> opponentTeamMatch.setTeamName(opponentTeam)
                );

        // Add both TeamMatch entities to the match
        match.setMatchStats(new ArrayList<>(List.of(coachTeamMatch, opponentTeamMatch)));

        // Save cascade will persist everything correctly
        return matchRepository.save(match);
    }



}
