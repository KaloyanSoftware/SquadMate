package com.portfolio.squadmate.service;

import com.portfolio.squadmate.domain.Coach;
import com.portfolio.squadmate.domain.Player;
import com.portfolio.squadmate.domain.User;

public class RoleManagementFactory {

    public static User getUser(String role) {
        if (role.equalsIgnoreCase("player")) {
            return new Player();
        }else{
            return new Coach();
        }
    }
}
