package com.portfolio.squadmate.presentation.webControllers.player;

import com.portfolio.squadmate.domain.Match;
import com.portfolio.squadmate.presentation.webControllers.viewModels.MatchesViewModel;
import com.portfolio.squadmate.security.CustomUserDetails;
import com.portfolio.squadmate.service.PlayerService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/home")
    public ModelAndView showHome() {
        return new ModelAndView("player/player-home");
    }

    @GetMapping("/matches")
    public ModelAndView showMatches(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        List<Match> upcomingMatches = playerService.findUpcomingMatches(customUserDetails.getId());

        ModelAndView modelAndView = new ModelAndView("player/player-matches");

        modelAndView.addObject("matches", MatchesViewModel.from(upcomingMatches));

        return modelAndView;
    }
}
