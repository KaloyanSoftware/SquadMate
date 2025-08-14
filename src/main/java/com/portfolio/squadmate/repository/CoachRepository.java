package com.portfolio.squadmate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.portfolio.squadmate.domain.Coach;
import org.springframework.data.jpa.repository.Query;

public interface CoachRepository extends JpaRepository<Coach, Integer> {

    @Query("""
            SELECT c
            FROM Coach c
            LEFT JOIN FETCH c.team
            WHERE c.id = :id
            """)
    Coach getCoachWithTeam(Integer id);
}
