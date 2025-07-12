package com.portfolio.squadmate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.portfolio.squadmate.domain.Coach;

public interface CoachRepository extends JpaRepository<Coach, Integer> {}
