package com.example.restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {

    @GetMapping("/login")
    public String getLoginView(){
        return "login";
    }

    @GetMapping("/")
    public String getLoginFromBlanc(){
        return "login";
    }


    @GetMapping("/home")
    public String getCourses(){
        return "home";
    }

    @GetMapping("/access-denied")
    public String accessDenied(){
        return "access-denied";
    }

}
