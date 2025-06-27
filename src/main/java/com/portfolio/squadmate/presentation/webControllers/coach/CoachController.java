package com.portfolio.squadmate.presentation.webControllers.coach;

import com.portfolio.squadmate.presentation.webControllers.viewModels.TeamWithPlayersViewModel;
import com.portfolio.squadmate.security.CustomUserDetails;
import com.portfolio.squadmate.service.TeamService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/coach")
public class CoachController {

    private final TeamService teamService;

    public CoachController(final TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/home")
    public ModelAndView showHome() {
        return new ModelAndView("coach/coach-home");
    }

    @GetMapping("/teamManager")
    public ModelAndView showTeamManager(@AuthenticationPrincipal final CustomUserDetails customUserDetails) {
        final ModelAndView modelAndView = new ModelAndView("coach/coach-teamManager");
        modelAndView.addObject("team", TeamWithPlayersViewModel.from(teamService.findByCoachId(customUserDetails.getId())));
        return modelAndView;
    }

}
