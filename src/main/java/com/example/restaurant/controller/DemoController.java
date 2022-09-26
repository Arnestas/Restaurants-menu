package com.example.restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/hello")
    public String sayhello(Model model){

        model.addAttribute("date", "Siandien yra ketvirtadienis");

        return "hello";
    }
}
