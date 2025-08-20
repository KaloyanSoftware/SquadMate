package com.portfolio.squadmate.presentation.webApi;

import com.portfolio.squadmate.domain.Player;
import com.portfolio.squadmate.presentation.webApi.dto.PatchPlayerDTO;
import com.portfolio.squadmate.presentation.webApi.dto.PlayerDTO;
import com.portfolio.squadmate.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/player")
public class ApiPlayerController{

    private final PlayerService playerService;

    public ApiPlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PreAuthorize("@authorizationService.canModifyPlayer(principal, #playerId)")
    @PatchMapping("/{id}")
    public ResponseEntity<PlayerDTO> patch(@PathVariable final String id,
                                           @RequestBody final PatchPlayerDTO patchPlayerDTO) {
        Player player = playerService.patchPlayer(Integer.parseInt(id), patchPlayerDTO.jerseyNumber(), patchPlayerDTO.position(),
                patchPlayerDTO.isStarter());

        return ResponseEntity.status(HttpStatus.OK).body(PlayerDTO.from(player));
    }


}
