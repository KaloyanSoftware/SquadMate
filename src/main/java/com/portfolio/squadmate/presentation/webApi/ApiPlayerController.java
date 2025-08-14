package com.portfolio.squadmate.presentation.webApi;

import com.portfolio.squadmate.presentation.webApi.dto.PatchPlayerDTO;
import com.portfolio.squadmate.presentation.webApi.dto.PlayerDTO;
import com.portfolio.squadmate.security.CustomUserDetails;
import com.portfolio.squadmate.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/player")
public class ApiPlayerController {

    private final PlayerService playerService;

    public ApiPlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PreAuthorize("@authorizationService.isCoach(principal)")
    @PatchMapping("/{id}")
    public ResponseEntity<PlayerDTO> patch(@PathVariable final String id,
                                           @AuthenticationPrincipal final CustomUserDetails customUserDetails,
                                           @RequestBody final PatchPlayerDTO patchPlayerDTO) {

        final PlayerDTO playerDTO = PlayerDTO.from(playerService.addPlayerToTeam(Integer.parseInt(id),
                patchPlayerDTO.jerseyNumber(), patchPlayerDTO.position(),
                customUserDetails.getId()));
        return ResponseEntity.status(HttpStatus.OK).body(playerDTO);
    }


}
