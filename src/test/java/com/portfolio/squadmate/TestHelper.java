package com.portfolio.squadmate;

import com.portfolio.squadmate.domain.*;
import com.portfolio.squadmate.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        user.setPassword("1234");
        return (Coach) userRepository.save(user);
    }

    public Player createPlayer(final int jerseyNumber, final Position position, final boolean isStarter){
        Player player = new Player();
        player.setJerseyNumber(jerseyNumber);
        player.setPosition(position);
        player.setStarter(isStarter);
        return playerRepository.save(player);
    }

    public Team createTeam(final Coach coach){
        Team team = new Team();
        team.setName("test-team");
        team.setCoach(coach);
        return teamRepository.save(team);
    }

    public Coach createCoach(final String email) {
        final Coach coach = new Coach();
        coach.setEmail(email);
        return coachRepository.save(coach);
    }

    public Player createPlayerWithTeam(final int jerseyNumber, final Position position, final boolean isStarter, final Team team){
        Player player = new Player();
        player.setJerseyNumber(jerseyNumber);
        player.setPosition(position);
        player.setStarter(isStarter);
        player.setTeam(team);
        return playerRepository.save(player);
    }
}
