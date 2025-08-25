package com.portfolio.squadmate.service;

import com.portfolio.squadmate.domain.Coach;
import com.portfolio.squadmate.domain.Match;
import com.portfolio.squadmate.domain.Player;
import com.portfolio.squadmate.domain.Team;
import com.portfolio.squadmate.repository.CoachRepository;
import com.portfolio.squadmate.repository.MatchRepository;
import com.portfolio.squadmate.repository.PlayerRepository;
import com.portfolio.squadmate.repository.TeamRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Transactional
public class TeamService {
    private final TeamRepository teamRepository;

    private final CoachRepository coachRepository;

    private final PlayerRepository playerRepository;

    private final MatchRepository matchRepository;

    public TeamService(final TeamRepository teamRepository, final CoachRepository coachRepository,
                       final PlayerRepository playerRepository, final MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.coachRepository = coachRepository;
        this.playerRepository = playerRepository;
        this.matchRepository = matchRepository;
    }

    public Team findByCoachId(final Integer id){
        return teamRepository.getTeamWithPlayersByCoachId(id).orElseThrow();
    }

    public boolean noCurrentTeam(final Integer id){

        return teamRepository.getTeamByCoachId(id).isEmpty();
    }

    public Team createTeam(final String teamName, final Integer coachId) {
        final Team team = new Team();
        final Coach coach = coachRepository.findById(coachId).orElseThrow();
        team.setName(teamName);
        team.setCoach(coach);
        return teamRepository.save(team);
    }

    public void removePlayer(final Integer playerId){
        final Player player = playerRepository.findById(playerId).orElseThrow();
        player.setTeam(null);
    }

    public List<Match> findAllMatches(final Integer userId){
        Coach coach = coachRepository.getCoachWithTeam(userId).orElseThrow();
        return matchRepository.getAllMatchesByTeamId(coach.getTeam().getId());
    }
}
