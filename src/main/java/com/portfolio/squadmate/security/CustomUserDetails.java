package com.portfolio.squadmate.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.Collection;

@Getter
public class CustomUserDetails extends User {

    private final int id;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, int id) {
        super(username, password, authorities);
        this.id = id;
    }
}
