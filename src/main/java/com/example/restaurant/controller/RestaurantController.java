package com.example.restaurant.controller;

import com.example.restaurant.entity.Restaurant;
import com.example.restaurant.service.RestaurantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/restaurants")
public class RestaurantController {

    private RestaurantService restaurantService;

    // Constructor injection
    public RestaurantController(RestaurantService theRestaurantService){
        restaurantService = theRestaurantService;
    }

    @GetMapping("/list")
    public String listRestaurants(Model model){

        // get restaurants from db
        List<Restaurant> restaurants = restaurantService.findAll();

        // add to the spring model
        model.addAttribute("restaurants", restaurants);

        return "restaurants/list-restaurants";
    }

    @GetMapping("/showFormForAdd")
    private String showFormForAdd(Model model){

        Restaurant restaurant = new Restaurant();

        model.addAttribute("restaurant", restaurant);

        return "restaurants/restaurant-form";

    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("restaurantId") int id, Model model){

        Restaurant restaurant = restaurantService.findById(id);

        model.addAttribute("restaurant", restaurant);

        return "restaurants/restaurant-form";
    }

    @PostMapping("/save")
    public String saveRestaurant(@ModelAttribute("restaurant") Restaurant restaurant){

        restaurantService.save(restaurant);

        return "redirect:/restaurants/list";
    }

    @GetMapping("/delete")
    public String deleteRestaurant(@RequestParam("restaurantId") int id){

        restaurantService.deleteById(id);

        return "redirect:/restaurants/list";
    }

}
