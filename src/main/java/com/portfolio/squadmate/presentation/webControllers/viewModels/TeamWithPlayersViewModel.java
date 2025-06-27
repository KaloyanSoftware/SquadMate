package com.portfolio.squadmate.presentation.webControllers.viewModels;

import com.portfolio.squadmate.domain.Team;

import java.util.List;

public record TeamWithPlayersViewModel(String name, List<PlayerViewModel> playerViewModels) {
    public static TeamWithPlayersViewModel from(final Team team){
        return new TeamWithPlayersViewModel(team.getName(), team.getPlayers().stream().map(PlayerViewModel::from).toList());
    }
}
