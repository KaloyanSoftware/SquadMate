package com.portfolio.squadmate.presentation.webApi.dto;

import com.portfolio.squadmate.domain.Position;

public record PatchPlayerDTO(int jerseyNumber, Position position) {
}
