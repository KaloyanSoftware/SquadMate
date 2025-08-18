package com.portfolio.squadmate.repository;

import com.portfolio.squadmate.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    @Query("""
            SELECT p
            FROM Player p
            LEFT JOIN FETCH p.team
            WHERE p.id = :id
            """)
    Player getPlayerByIdWithTeam(Integer id);
}
