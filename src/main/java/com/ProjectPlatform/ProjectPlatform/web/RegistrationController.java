package com.ProjectPlatform.ProjectPlatform.web;

import com.ProjectPlatform.ProjectPlatform.user.UserService;
import com.ProjectPlatform.ProjectPlatform.web.dto.UserDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(path = "/")
public class RegistrationController {

    private UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserDto userDto() {
        return new UserDto();
    }

    @GetMapping("/authorization")
    public ModelAndView userRegistration(){
        return new ModelAndView("sign");
    }

    @PostMapping("/authorization/save")
    public ModelAndView saveUser(@ModelAttribute("user") UserDto userDto) {
        userService.save(userDto);
        return new ModelAndView("redirect:/registration?success");
    }

}
