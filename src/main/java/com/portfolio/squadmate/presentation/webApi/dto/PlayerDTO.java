package com.portfolio.squadmate.presentation.webApi.dto;

import com.portfolio.squadmate.domain.Player;
import com.portfolio.squadmate.domain.Position;

public record PlayerDTO(Integer playerId, int jerseyNumber, Position position, boolean isStarter, String teamName) {
    public static PlayerDTO from(final Player player){
        return new PlayerDTO(player.getId(), player.getJerseyNumber(), player.getPosition(), player.isStarter(),
                player.getTeam().getName());
    }
}
