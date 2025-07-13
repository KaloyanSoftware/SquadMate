package com.portfolio.squadmate.presentation.webControllers;

import com.portfolio.squadmate.domain.Coach;
import com.portfolio.squadmate.domain.Player;
import com.portfolio.squadmate.presentation.webControllers.viewModels.NewUserViewModel;
import com.portfolio.squadmate.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        modelAndView.addObject("newUserViewModel", new NewUserViewModel());
        modelAndView.addObject("roles", List.of(Player.class.getSimpleName(), Coach.class.getSimpleName()));
        return modelAndView;
    }

    @PostMapping("register")
    public String registerNewUser(@Valid @ModelAttribute("newUserViewModel") NewUserViewModel newUserViewModel,
                                  BindingResult bindingResult,
                                  Model model) {

        if (bindingResult.hasErrors()) {
            // Pass the form back with errors
            model.addAttribute("roles", List.of(Player.class.getSimpleName(), Coach.class.getSimpleName()));
            return "register";
        }

        userService.createNewUser(
                newUserViewModel.getFirstName(),
                newUserViewModel.getLastName(),
                newUserViewModel.getEmail(),
                newUserViewModel.getPassword(),
                newUserViewModel.getBirthDate(),
                newUserViewModel.getRole());

        return "redirect:/login";
    }
}
