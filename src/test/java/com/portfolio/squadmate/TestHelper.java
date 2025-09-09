package com.portfolio.squadmate;

import com.portfolio.squadmate.domain.Coach;
import com.portfolio.squadmate.domain.User;
import com.portfolio.squadmate.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TestHelper {

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    public void cleanup(){
        coachRepository.deleteAll();
        matchRepository.deleteAll();
        playerRepository.deleteAll();
        teamRepository.deleteAll();
        userRepository.deleteAll();
    }

    public Coach createCoachUser(){
        final User user = new Coach();
        user.setEmail("testCoach1@gmail.com");
        //pass: testcoach1
        user.setPassword("$2a$12$vfSc.ETeOWUxsiDw5eqyNu/pYoChpQb1GhF4W02GOnl/LRwitBCxm");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setBirthDate(LocalDate.of(1987, 3, 2));
        return (Coach) userRepository.save(user);
    }

}
