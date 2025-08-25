package com.portfolio.squadmate.presentation.webControllers.coach;

import com.portfolio.squadmate.domain.Match;
import com.portfolio.squadmate.domain.Position;
import com.portfolio.squadmate.presentation.webControllers.viewModels.MatchesViewModel;
import com.portfolio.squadmate.presentation.webControllers.viewModels.TeamWithPlayersViewModel;
import com.portfolio.squadmate.security.CustomUserDetails;
import com.portfolio.squadmate.service.AuthorizationService;
import com.portfolio.squadmate.service.TeamService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/coach")
public class CoachController {

    private final TeamService teamService;

    private final AuthorizationService authorizationService;

    public CoachController(final TeamService teamService, final AuthorizationService authorizationService) {
        this.teamService = teamService;
        this.authorizationService = authorizationService;
    }

    @GetMapping("/home")
    public ModelAndView showHome() {
        return new ModelAndView("coach/coach-home");
    }

    @GetMapping("/teamManager")
    public ModelAndView showTeamManager(@AuthenticationPrincipal final CustomUserDetails customUserDetails) {
        if(teamService.noCurrentTeam(customUserDetails.getId())){
            return new ModelAndView("redirect:/coach/createTeam");
        }else{
        final ModelAndView modelAndView = new ModelAndView("coach/coach-teamManager");
        modelAndView.addObject("team", TeamWithPlayersViewModel.from(teamService.findByCoachId(customUserDetails.getId())));

        modelAndView.addObject("positions", Position.values());

        modelAndView.addObject("availableJerseyNumbers", teamService
                .findByCoachId(customUserDetails.getId()).getAvailableJerseyNumbers());
        return modelAndView;
       }
    }

    @GetMapping("/createTeam")
    public ModelAndView showCreateTeam() {
        return new ModelAndView("coach/coach-create-team");
    }

    @GetMapping("/addPlayer")
    public ModelAndView showAddPlayer(@AuthenticationPrincipal final CustomUserDetails customUserDetails) {

        if(authorizationService.isCoachWithNoTeam(customUserDetails)){
            return new ModelAndView("redirect:/coach/createTeam");
        }else{
            final ModelAndView modelAndView = new ModelAndView("coach/coach-add-player");

            modelAndView.addObject("availableJerseyNumbers",teamService
                    .findByCoachId(customUserDetails.getId()).getAvailableJerseyNumbers());

            modelAndView.addObject("positions", Position.values());

            return modelAndView;
        }
    }

    @GetMapping("/fixtures")
    public ModelAndView showFixtures(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        List<Match> matches = teamService.findAllMatches(customUserDetails.getId());

        final ModelAndView modelAndView = new ModelAndView("coach/coach-fixtures");

        modelAndView.addObject("matches", MatchesViewModel.from(matches));

        return modelAndView;
    }
}
