package com.portfolio.squadmate.presentation.webControllers;

import com.portfolio.squadmate.domain.Coach;
import com.portfolio.squadmate.domain.Player;
import com.portfolio.squadmate.presentation.webControllers.viewModels.NewUserViewModel;
import com.portfolio.squadmate.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
@RequestMapping("/")
public class BaseController {

    private final UserService userService;

    public BaseController(UserService userService) {
        this.userService = userService;
    }

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

    @PostMapping("register")
    public String registerNewUser(
            NewUserViewModel newUserViewModel) {
        userService.createNewUser(newUserViewModel.firstName(), newUserViewModel.lastName(), newUserViewModel.email(),
                newUserViewModel.password(), newUserViewModel.birthDate(), newUserViewModel.role() );

        return "redirect:/login";
    }
}
