package com.portfolio.squadmate.presentation.webApi;

import com.portfolio.squadmate.domain.Match;
import com.portfolio.squadmate.presentation.webApi.dto.AddMatchDTO;
import com.portfolio.squadmate.presentation.webApi.dto.MatchDTO;
import com.portfolio.squadmate.security.CustomUserDetails;
import com.portfolio.squadmate.service.MatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/match")
public class ApiMatchController {

    private final MatchService matchService;

    public ApiMatchController(final MatchService matchService) {
        this.matchService = matchService;
    }

    @PreAuthorize("!@authorizationService.isCoachWithNoTeam(principal)")
    @PostMapping
    public ResponseEntity<MatchDTO> post(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                         @RequestBody AddMatchDTO addMatchDTO){

        final Match match = matchService.createMatch(customUserDetails.getId(), addMatchDTO.matchDate(), addMatchDTO.location(),
                addMatchDTO.opponentTeam());

        return ResponseEntity.status(HttpStatus.CREATED).body(MatchDTO.from(match));
    }

}
