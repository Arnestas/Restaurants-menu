package com.example.restaurant.controller;

import com.example.restaurant.entity.Dish;
import com.example.restaurant.entity.DishInMenu;
import com.example.restaurant.entity.Menu;
import com.example.restaurant.entity.Restaurant;
import com.example.restaurant.service.DishInMenuService;
import com.example.restaurant.service.DishService;
import com.example.restaurant.service.MenuService;
import com.example.restaurant.service.RestaurantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/menus")
public class MenuController {

    private MenuService menuService;
    private RestaurantService restaurantService;
    private DishService dishService;

    private DishInMenuService dishInMenuService;

    public MenuController(MenuService menuService, RestaurantService restaurantService, DishService dishService, DishInMenuService dishInMenuService) {
        this.menuService = menuService;
        this.restaurantService = restaurantService;
        this.dishService = dishService;
        this.dishInMenuService = dishInMenuService;
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
        String message = "Choose a restaurant";

        // get restaurants from db
        List<Restaurant> restaurants = restaurantService.findAll();
        // add to the spring model
        model.addAttribute("restaurants", restaurants);
        menu.setRestaurantId(restaurant.getId());//

        model.addAttribute("menu", menu);

        return "menus/menu-form";

    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("menuId") int id, Model model){

        Menu menu = menuService.findById(id);

        model.addAttribute("menu", menu);

        return "menus/menu-form";
    }

    @GetMapping("/showFormToAddDish")
    public String showFormToAddDish(@RequestParam("menuId") int id, Model model){

        Menu menu = menuService.findById(id);

        Dish dish = new Dish();

        DishInMenu dishInMenu = new DishInMenu();

        List<Dish> dishes = dishService.findAll();
        model.addAttribute("menu", menu);
        model.addAttribute("dishes", dishes);

        model.addAttribute("dishInMenu", dishInMenu);

        return "menus/edit-menu-form";
    }

    @PostMapping("/saveDishInMenu")
    public String saveDishInMenu(@ModelAttribute("dishInMenu") DishInMenu dishInMenu){

        dishInMenuService.save(dishInMenu);

        return "redirect:/menus/list";
    }

    @PostMapping("/save")
    public String saveMenu(@ModelAttribute("menu") Menu menu){

        menuService.save(menu);

        return "redirect:/menus/list";
    }

    @GetMapping("/delete")
    public String deleteMenu(@RequestParam("menuId") int id){

        menuService.deleteById(id);

        return "redirect:/menus/list";
    }

    @GetMapping("/deleteDishInMenu")
    public String deleteDishInMenu(@RequestParam("dishId") int id){

        dishInMenuService.deleteById(id);

        return "redirect:/menus/showFormToAddDish";
    }

}
