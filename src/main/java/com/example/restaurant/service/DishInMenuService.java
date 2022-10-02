package com.example.restaurant.service;

import com.example.restaurant.entity.Dish;
import com.example.restaurant.entity.DishInMenu;

import java.util.List;

public interface DishInMenuService {

    public List<DishInMenu> findAll();

    public DishInMenu findById(int id);

    public void save(DishInMenu dishInMenu);

    public void deleteById(int id);

}
