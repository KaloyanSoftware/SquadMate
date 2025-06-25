package com.portfolio.squadmate.presentation.webControllers.coach;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/coach/home")
public class CoachController {

    @GetMapping
    public ModelAndView showHome() {
        return new ModelAndView("coach/coach-home");
    }
}
