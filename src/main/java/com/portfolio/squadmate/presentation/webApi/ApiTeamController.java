package com.portfolio.squadmate.presentation.webApi;

import com.portfolio.squadmate.presentation.webApi.dto.PatchPlayerDTO;
import com.portfolio.squadmate.presentation.webApi.dto.PlayerDTO;
import com.portfolio.squadmate.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.portfolio.squadmate.domain.Team;
import com.portfolio.squadmate.presentation.webApi.dto.AddTeamDTO;
import com.portfolio.squadmate.presentation.webApi.dto.TeamDTO;
import com.portfolio.squadmate.security.CustomUserDetails;
import com.portfolio.squadmate.service.TeamService;

@RestController
@RequestMapping("/api/team")
public class ApiTeamController {

    private final TeamService teamService;

    private final PlayerService playerService;

    public ApiTeamController(final TeamService teamService, final PlayerService playerService) {
        this.teamService = teamService;
        this.playerService = playerService;
    }

    @PreAuthorize("@authorizationService.isCoachWithNoTeam(principal)")
    @PostMapping
    public ResponseEntity<TeamDTO> createTeam(@RequestBody final AddTeamDTO addTeamDTO,
    @AuthenticationPrincipal final CustomUserDetails customUserDetails) {
        final Team team = teamService.createTeam(addTeamDTO.teamName(), customUserDetails.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(TeamDTO.from(team));
    }

    @PreAuthorize("@authorizationService.isCoach(principal)")
    @PatchMapping("players/{id}")
    public ResponseEntity<PlayerDTO> addPlayerToTeam(@PathVariable final String id,
                                                       @AuthenticationPrincipal final CustomUserDetails customUserDetails,
                                                       @RequestBody final PatchPlayerDTO patchPlayerDTO) {

        final PlayerDTO playerDTO = PlayerDTO.from(playerService.addPlayerToTeam(Integer.parseInt(id),
                patchPlayerDTO.jerseyNumber(), patchPlayerDTO.position(),
                customUserDetails.getId()));
        return ResponseEntity.status(HttpStatus.OK).body(playerDTO);
    }

    @PreAuthorize("@authorizationService.canModifyPlayer(principal, #playerId)")
    @DeleteMapping("/players/{playerId}")
    public ResponseEntity<Void> removePlayer(@PathVariable final Integer playerId) {

        teamService.removePlayer(playerId);

        return ResponseEntity.noContent().build();
    }
}
