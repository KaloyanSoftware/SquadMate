package com.portfolio.squadmate.repository;

import com.portfolio.squadmate.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);


    /*@Query("""
        FROM User u
        LEFT JOIN FETCH u.t
        WHERE u.id = :id
        """)
    Optional<User> findByIdWithTeam(Integer id);*/
}
