package com.example.restaurant.controller;

import com.example.restaurant.entity.Menu;
import com.example.restaurant.entity.Restaurant;
import com.example.restaurant.service.MenuService;
import com.example.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/menus")
public class MenuController {

    private MenuService menuService;
    private RestaurantService restaurantService;

    public MenuController(MenuService menuService, RestaurantService restaurantService) {
        this.menuService = menuService;
        this.restaurantService = restaurantService;
    }

    @GetMapping("/list")
    public String listMenu(Model model){

        List<Menu> menus = menuService.findAll();

        //Menu menu = new Menu();//
        //Restaurant restaurant = new Restaurant();//

        model.addAttribute("menus", menus);
        //model.addAttribute("restaurants", restaurant);//

        return "menus/list-menu";
    }


    @GetMapping("/showFormForAdd")
    private String showFormForAdd(Model model){

        Menu menu = new Menu();
        Restaurant restaurant = new Restaurant(); //





        // get restaurants from db
        List<Restaurant> restaurants = restaurantService.findAll();//
        // add to the spring model
        model.addAttribute("restaurants", restaurants);//
        menu.setRestaurantId(restaurant.getId());//
        System.out.println(menu.getName());
        System.out.println(restaurant.getId());
        model.addAttribute("menu", menu);

        return "menus/menu-form";

    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("menuId") int id, Model model){

        Menu menu = menuService.findById(id);

        model.addAttribute("menu", menu);

        return "menus/menu-form";
    }

    @PostMapping("/save")
    public String saveMenu(@ModelAttribute("menu") Menu menu){

        menuService.save(menu);

        return "redirect:/menus/list";
    }

    @GetMapping("/delete")
    public String deleteRestaurant(@RequestParam("menuId") int id){

        menuService.deleteById(id);

        return "redirect:/menus/list";
    }



}
