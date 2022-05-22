package com.example.tringleAtm.Controllers;

import com.example.tringleAtm.Models.User;
import com.example.tringleAtm.Services.Implementation.UserServiceImpl;
import com.example.tringleAtm.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final UserService userService;
    private User loggedUser = null;

    @Autowired
    public LoginController(UserServiceImpl userServiceImpl) {
        this.userService = userServiceImpl;
    }


    @PostMapping("/login")
    public String user(Model userModel) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            loggedUser = userService.findByUsername(authentication.getName()).get();
        }

        userModel.addAttribute("loggedInUser", loggedUser);
        return "userPage";
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam("depositAmount") Long depositAmount, Model userModel) {

        if (depositAmount != null) {
            loggedUser.setDeposit(loggedUser.getDeposit() + depositAmount);
            userService.saveUser(loggedUser);
        }
        userModel.addAttribute("loggedInUser", loggedUser);
        return "userPage";
    }


    @PostMapping("/withdraw")
    public String withdraw(@RequestParam("withdrawAmount") Long withdrawAmount, Model userModel) {
        if (withdrawAmount == null) {

            withdrawAmount = 0L;
        }
        if (withdrawAmount < loggedUser.getDeposit()) {
            loggedUser.setDeposit(loggedUser.getDeposit() - withdrawAmount);
            userService.saveUser(loggedUser);
        }
        userModel.addAttribute("loggedInUser", loggedUser);
        return "userPage";
    }

    @GetMapping(path = "/logout")
    public String logout() {
        loggedUser = null;
        return "logout";
    }

}
