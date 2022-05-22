package com.example.tringleAtm.Controllers;

import com.example.tringleAtm.Models.User;
import com.example.tringleAtm.Services.Implementation.UserServiceImpl;
import com.example.tringleAtm.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserController(UserServiceImpl userService , BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userService = userService;
        this.passwordEncoder = bCryptPasswordEncoder;
    }

    @RequestMapping(path = "/register",method = RequestMethod.POST)
    public String saveUser(@ModelAttribute User user){
//      Checks if the User is already registered
        if(!this.checkIfExists(user.getFirstName(), user.getLastName())){
            user.setPassword(this.passwordEncoder.encode(user.getPassword()));
            user.setDeposit(0L);
            user.setActive(true);
            user.setRole("USER");
            userService.saveUser(user);
        }
        return "index";
    }


    private boolean checkIfExists(String firstName , String lastName){
        return userService.existsByFirstNameAndLastName(firstName , lastName);
    }

    @RequestMapping(path = "/register" , method = RequestMethod.GET)
    public String getResiter(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @GetMapping(path = "/home")
    public String goHome(){
        return "index";
    }





}
