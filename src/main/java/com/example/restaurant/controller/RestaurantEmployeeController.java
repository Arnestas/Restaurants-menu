package com.example.restaurant.controller;

import com.example.restaurant.entity.DishInMenu;
import com.example.restaurant.entity.Menu;
import com.example.restaurant.entity.Restaurant;
import com.example.restaurant.service.DishInMenuService;
import com.example.restaurant.service.MenuService;
import com.example.restaurant.service.RestaurantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/restaurants_employee")
public class RestaurantEmployeeController {

    private RestaurantService restaurantService;
    private MenuService menuService;
    private DishInMenuService dishInMenuService;

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

    @GetMapping("/show-menu")       //  pridedu menu restoranui
    public String listMenu(@RequestParam("menuId") int id,  Model model){

        List<Menu> menus = menuService.findAll();
        List<DishInMenu> dishInMenus= dishInMenuService.findAll();

        //Menu menu = new Menu();//
        //Restaurant restaurant = new Restaurant();//

        model.addAttribute("menus", menus);
        model.addAttribute("dishInMenus", dishInMenus);
        //model.addAttribute("restaurants", restaurant);

        return "restaurants_employee/list-menu";
    }


}
