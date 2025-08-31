package com.portfolio.squadmate.service;

import com.portfolio.squadmate.domain.*;
import com.portfolio.squadmate.repository.*;
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

    private final UserRepository userRepository;

    public TeamService(final TeamRepository teamRepository, final CoachRepository coachRepository,
                       final PlayerRepository playerRepository, final MatchRepository matchRepository,
                       final UserRepository userRepository) {
        this.teamRepository = teamRepository;
        this.coachRepository = coachRepository;
        this.playerRepository = playerRepository;
        this.matchRepository = matchRepository;
        this.userRepository = userRepository;
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

    public List<Match> findAllMatches(final Integer userId) {
        final User user = userRepository.findById(userId).orElseThrow();

        Integer teamId = null;

        if (user instanceof Coach coach) {
            teamId = coachRepository.getCoachWithTeam(coach.getId())
                    .map(Coach::getTeam)
                    .map(Team::getId)
                    .orElseThrow();
        } else if (user instanceof Player player) {
            teamId = playerRepository.getPlayerByIdWithTeam(player.getId())
                    .map(Player::getTeam)
                    .map(Team::getId)
                    .orElseThrow();
        }

        if (teamId == null) {
            throw new IllegalArgumentException("User is neither a Coach nor a Player with a team");
        }

        return matchRepository.getAllMatchesByTeamId(teamId);
    }

}
