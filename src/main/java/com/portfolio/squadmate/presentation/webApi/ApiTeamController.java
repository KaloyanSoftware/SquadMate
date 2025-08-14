package com.portfolio.squadmate.presentation.webApi;

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

    public ApiTeamController(final TeamService teamService) {
        this.teamService = teamService;
    }

    @PreAuthorize("@authorizationService.isCoachWithNoTeam(principal)")
    @PostMapping
    public ResponseEntity<TeamDTO> createTeam(@RequestBody final AddTeamDTO addTeamDTO,
    @AuthenticationPrincipal final CustomUserDetails customUserDetails) {
        final Team team = teamService.createTeam(addTeamDTO.teamName(), customUserDetails.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(TeamDTO.from(team));
    }

    //still having doubts which controller should handle this endpoint
    //what I want to achieve here is to remove a player from a team
    @DeleteMapping("/players/{playerId}")
    public ResponseEntity<Void> removePlayer(@PathVariable final Integer playerId) {

        teamService.removePlayer(playerId);

        return ResponseEntity.noContent().build();
    }
}
