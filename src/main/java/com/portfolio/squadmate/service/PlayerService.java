package com.portfolio.squadmate.service;

import com.portfolio.squadmate.domain.Player;
import com.portfolio.squadmate.domain.Position;
import com.portfolio.squadmate.domain.Team;
import com.portfolio.squadmate.repository.PlayerRepository;
import com.portfolio.squadmate.repository.TeamRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PlayerService{

    private final PlayerRepository playerRepository;

    private final TeamRepository teamRepository;

    public PlayerService(final PlayerRepository playerRepository,
                         final TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    public Player patch(final Integer id, final int jerseyNumber, final Position position, final Integer coachId){
        final Player player = playerRepository.findById(id).orElseThrow();
        final Team team = teamRepository.getTeamWithPlayersByCoachId(coachId);

        if(jerseyNumber != 0){
            player.setJerseyNumber(jerseyNumber);
        }

        if(position != null){
            player.setPosition(position);
        }

        if(coachId != null &&  team != null){
            player.setTeam(team);
        }

        return playerRepository.save(player);
    }

}
