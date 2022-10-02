package com.example.restaurant.controller;

import com.example.restaurant.entity.Restaurant;
import com.example.restaurant.service.RestaurantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/restaurants_employee")
public class RestaurantEmployeeController {

    private RestaurantService restaurantService;

    // Constructor injection
    public RestaurantEmployeeController(RestaurantService theRestaurantService){
        restaurantService = theRestaurantService;
    }

    @GetMapping("/list")
    public String listRestaurants(Model model){

        // get restaurants from db
        List<Restaurant> restaurants = restaurantService.findAll();

        // add to the spring model
        model.addAttribute("restaurants", restaurants);

        return "restaurants_employee/list-restaurants";
    }


}
