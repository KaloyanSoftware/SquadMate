package com.portfolio.squadmate.presentation.webControllers.player;

import com.portfolio.squadmate.domain.Match;
import com.portfolio.squadmate.presentation.webControllers.viewModels.MatchesViewModel;
import com.portfolio.squadmate.security.CustomUserDetails;
import com.portfolio.squadmate.service.TeamService;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
@RequestMapping("/player")
@Log4j2
public class PlayerController {

    private final TeamService teamService;

    public PlayerController(final TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/home")
    public ModelAndView showHome() {
        return new ModelAndView("player/player-home");
    }

    @GetMapping("/matches")
    public ModelAndView showMatches(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        List<Match> matches = teamService.findAllMatches(customUserDetails.getId());
        log.error("Matches found: {}", matches.size());


        ModelAndView modelAndView = new ModelAndView("player/player-fixtures");

        modelAndView.addObject("matches", MatchesViewModel.from(matches));

        return modelAndView;
    }
}
