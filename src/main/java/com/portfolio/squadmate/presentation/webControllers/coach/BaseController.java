package com.portfolio.squadmate.presentation.webControllers.coach;

import com.portfolio.squadmate.domain.Coach;
import com.portfolio.squadmate.domain.Player;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class BaseController {

    @GetMapping
    public ModelAndView showLanding(){
        return new ModelAndView("landing");
    }

    @GetMapping("login")
    public ModelAndView login(){
        return new ModelAndView("login");
    }

    @GetMapping("register")
    public ModelAndView register(){
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");

        modelAndView.addObject("roles", List.of(Player.class.getSimpleName(), Coach.class.getSimpleName()));
        return modelAndView;
    }
}
