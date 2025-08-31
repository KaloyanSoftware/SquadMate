package com.portfolio.squadmate.service;

import com.portfolio.squadmate.domain.*;
import com.portfolio.squadmate.repository.PlayerRepository;
import com.portfolio.squadmate.repository.MatchRepository;
import com.portfolio.squadmate.repository.TeamRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PlayerService{

    private final PlayerRepository playerRepository;

    private final TeamRepository teamRepository;

    private final MatchRepository matchRepository;

    public PlayerService(final PlayerRepository playerRepository,
                         final TeamRepository teamRepository, final MatchRepository matchRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    public Player addPlayerToTeam(final Integer id, final int jerseyNumber, final Position position, final Integer coachId){
        final Player player = playerRepository.findById(id).orElseThrow();
        final Team team = teamRepository.getTeamWithPlayersByCoachId(coachId).orElseThrow();

        if(jerseyNumber != 0){
            player.setJerseyNumber(jerseyNumber);
        }

        if(position != null){
            player.setPosition(position);
        }

        if(coachId != null){
            player.setTeam(team);
        }

        player.setStarter(true);
        return playerRepository.save(player);
    }

    public Player patchPlayer(final Integer id, final int jerseyNumber, final Position position, final boolean isStarter){
        final Player player = playerRepository.getPlayerByIdWithTeam(id).orElseThrow();

        if(jerseyNumber != 0){
            player.setJerseyNumber(jerseyNumber);
        }

        if(position != null){
            player.setPosition(position);
        }

        player.setStarter(isStarter);

        return playerRepository.save(player);
    }
}
