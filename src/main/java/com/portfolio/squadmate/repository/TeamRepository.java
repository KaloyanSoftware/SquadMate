package com.portfolio.squadmate.repository;

import com.portfolio.squadmate.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
    @Query("""
            SELECT t
            FROM Team t
            LEFT JOIN FETCH t.players
            WHERE t.coach.id = :id
            """)
    Optional<Team> getTeamWithPlayersByCoachId(Integer id);


    @Query("""
            SELECT t
            FROM Team t
            WHERE t.coach.id = :id
            """)
    Optional<Team> getTeamByCoachId(Integer id);
}
