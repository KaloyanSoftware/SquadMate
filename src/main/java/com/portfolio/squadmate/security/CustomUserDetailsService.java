package com.portfolio.squadmate.security;

import com.portfolio.squadmate.domain.Coach;
import com.portfolio.squadmate.domain.Player;
import com.portfolio.squadmate.domain.User;
import com.portfolio.squadmate.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws NotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new NotFoundException("User not found!"));

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (user instanceof Coach){
            authorities.add(new SimpleGrantedAuthority("ROLE_COACH"));
        } else if (user instanceof Player){
            authorities.add(new SimpleGrantedAuthority("ROLE_PLAYER"));
        }

        return new CustomUserDetails(user.getEmail(), user.getPassword(), authorities,user.getId());
    }
}
