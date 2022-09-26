package com.example.restaurant.controller;

import com.example.restaurant.entity.Dish;
import com.example.restaurant.service.DishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dishes")
public class DishController {

    private DishService dishService;

    public DishController(DishService dishService){
        this.dishService = dishService;
    }

    @GetMapping("/list")
    public String listDishes(Model model){

        List<Dish> dishes = dishService.findAll();

        model.addAttribute("dishes", dishes);

        return "dishes/list-dishes";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){

        Dish dish = new Dish();

        model.addAttribute("dish", dish);

        return "dishes/dish-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("dishId") int id, Model model){

        Dish dish = dishService.findById(id);

        model.addAttribute("dish", dish);

        return "dishes/dish-form";

    }

    @PostMapping("/save")
    public String saveDish(@ModelAttribute("dish") Dish dish){
        dishService.save(dish);
        return "redirect:/dishes/list";
    }

    @GetMapping("/delete")
    public String deleteDish(@RequestParam("dishId") int id){
        dishService.deleteById(id);
        return "redirect:/dishes/list";
    }


}
