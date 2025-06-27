package com.portfolio.squadmate.presentation.webControllers.viewModels;

import com.portfolio.squadmate.domain.Player;
import com.portfolio.squadmate.domain.Position;

public record PlayerViewModel(String firstName, String lastName, int jerseyNumber, Position position,
String profileImagePath) {
    public static PlayerViewModel from(final Player player){
        return new PlayerViewModel(player.getFirstName(), player.getLastName(),
                player.getJerseyNumber(), player.getPosition(), player.getProfileImagePath());
    }
}
