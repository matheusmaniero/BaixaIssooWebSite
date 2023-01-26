package com.baixaisso.baixaissoowebsite.controllers;

import com.baixaisso.baixaissoowebsite.model.User;
import com.baixaisso.baixaissoowebsite.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public String usertoreut(){


        User user =  userService.getUser("maniero87929783");

        return "null";
    }
}
