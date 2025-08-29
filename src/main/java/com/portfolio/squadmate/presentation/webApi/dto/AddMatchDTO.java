package com.portfolio.squadmate.presentation.webApi.dto;
import java.time.LocalDateTime;

public record AddMatchDTO(LocalDateTime matchDate,
                          String location,
                          String opponentTeam) {
}
