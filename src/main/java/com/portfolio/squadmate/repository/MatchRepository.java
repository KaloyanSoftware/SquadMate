package com.portfolio.squadmate.repository;

import com.portfolio.squadmate.domain.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Integer> {

    @Query("""
            SELECT m
            FROM Match m
            LEFT JOIN FETCH m.matchEvents me
            LEFT JOIN FETCH me.team t
            WHERE t.id = :id
                        AND m.matchDate > CURRENT_TIMESTAMP
            ORDER BY m.matchDate ASC
            """)
    List<Match> getUpcomingMatchesByTeamId(Integer id);

    @Query("""
            SELECT m
            FROM Match m
            LEFT JOIN FETCH m.matchEvents me
            LEFT JOIN FETCH me.team t
            LEFT JOIN FETCH t.coach c
            ORDER BY m.matchDate ASC
            """)
    List<Match> getAllMatchesByTeamId(Integer id);
}
