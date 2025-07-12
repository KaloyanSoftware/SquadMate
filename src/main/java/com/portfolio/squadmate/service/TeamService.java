package com.portfolio.squadmate.service;

import com.portfolio.squadmate.domain.Team;
import com.portfolio.squadmate.repository.TeamRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(final TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team findByCoachId(final Integer id){
        log.error("Coach id: {}",id);
        return teamRepository.getTeamWithPlayersByCoachId(id);
    }

    public boolean noCurrentTeam(final Integer id){
        return teamRepository.getTeamByCoachId(id) == null;
 }
}
