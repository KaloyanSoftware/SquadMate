package com.portfolio.squadmate.presentation.webApi.dto;

import com.portfolio.squadmate.domain.Team;

public record TeamDTO(Integer id, String name) {

    public static TeamDTO from(final Team team) {
        return new TeamDTO(team.getId(), team.getName());
    }
}
