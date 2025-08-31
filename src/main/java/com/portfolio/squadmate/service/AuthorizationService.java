package com.portfolio.squadmate.service;

import com.portfolio.squadmate.domain.Coach;
import com.portfolio.squadmate.domain.Player;
import com.portfolio.squadmate.domain.User;
import com.portfolio.squadmate.repository.CoachRepository;
import com.portfolio.squadmate.repository.PlayerRepository;
import com.portfolio.squadmate.repository.UserRepository;
import com.portfolio.squadmate.security.CustomUserDetails;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AuthorizationService {

    private final UserRepository userRepository;

    private final PlayerRepository playerRepository;

    private final CoachRepository coachRepository;

    public AuthorizationService(final UserRepository userRepository,
                                final PlayerRepository playerRepository,
                                final CoachRepository coachRepository) {
        this.userRepository = userRepository;
        this.playerRepository = playerRepository;
        this.coachRepository = coachRepository;
    }

    public boolean isCoachWithNoTeam(final CustomUserDetails customUserDetails){
        if(customUserDetails != null){
            final User user = userRepository.findById(customUserDetails.getId()).orElseThrow();
            if(user instanceof Coach coach){
                return noTeam(coach);
            }
        }
        return false;
    }



    private boolean noTeam(final Coach coach){
        return coach.getTeam() == null;
    }

    public boolean isCoach(final CustomUserDetails customUserDetails){
        if(customUserDetails != null){
            final User user = userRepository.findById(customUserDetails.getId()).orElseThrow();
            return user instanceof Coach;
        }
        return false;
    }

    public boolean canModifyPlayer(final CustomUserDetails customUserDetails,
                                   final Integer playerId){

        if(customUserDetails!=null){
            if(isCoach(customUserDetails)){
                Player player = playerRepository.getPlayerByIdWithTeam(playerId).orElseThrow();

                Coach coach = coachRepository.getCoachWithTeam(customUserDetails.getId()).orElseThrow();

                if(player.getTeam() != null){
                    return coach.getTeam().equals(player.getTeam());
                }
                return false;
            }
            return false;
        }
        return false;
    }



}
