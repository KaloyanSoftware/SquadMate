package com.portfolio.squadmate.repository;

import com.portfolio.squadmate.domain.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Integer> {

    @Query("""
    SELECT DISTINCT m
    FROM Match m
    LEFT JOIN FETCH m.matchStats me
    LEFT JOIN FETCH me.team t
    LEFT JOIN FETCH t.coach c
    WHERE EXISTS (
        SELECT 1
        FROM TeamMatch tm2
        WHERE tm2.match = m AND tm2.team.id = :teamId
    )
    ORDER BY m.matchDate ASC
""")
    List<Match> getAllMatchesByTeamId(@Param("teamId") Integer teamId);
}
