package com.portfolio.squadmate.repository;

import com.portfolio.squadmate.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
    @Query("""
            SELECT t
            FROM Team t
            JOIN FETCH t.players
            WHERE t.coach.id = :id
            """)
    Team getTeamWithPlayersByCoachId(Integer id);
}
