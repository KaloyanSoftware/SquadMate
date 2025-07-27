package com.portfolio.squadmate.service;

import com.portfolio.squadmate.domain.Coach;
import com.portfolio.squadmate.domain.User;
import com.portfolio.squadmate.repository.UserRepository;
import com.portfolio.squadmate.security.CustomUserDetails;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AuthorizationService {

    private final UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
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



}
