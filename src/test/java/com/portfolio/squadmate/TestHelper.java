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

    public Coach createCoachUser(final String email){
        final User user = new Coach();
        user.setEmail(email);
        //pass: testcoach1
        user.setPassword("1234");
        return (Coach) userRepository.save(user);
    }

}
