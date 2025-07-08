package com.portfolio.squadmate.service;

import com.portfolio.squadmate.domain.Team;
import com.portfolio.squadmate.repository.TeamRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(final TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team findByCoachId(final Integer id){
        return teamRepository.getTeamWithPlayersByCoachId(id);
    }


}
