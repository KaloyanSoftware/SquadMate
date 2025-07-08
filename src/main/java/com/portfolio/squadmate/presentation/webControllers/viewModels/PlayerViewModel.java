package com.portfolio.squadmate.presentation.webControllers.viewModels;

import com.portfolio.squadmate.domain.Player;

public record PlayerViewModel(String firstName, String lastName, int jerseyNumber, String position, boolean isStarter,
String profileImagePath) {
    public static PlayerViewModel from(final Player player){
        return new PlayerViewModel(player.getFirstName(), player.getLastName(),
                player.getJerseyNumber(), player.getPosition().name(), player.isStarter(), player.getProfileImagePath());
    }
}
