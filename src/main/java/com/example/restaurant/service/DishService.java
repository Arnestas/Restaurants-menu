package com.example.restaurant.service;

import com.example.restaurant.entity.Dish;

import java.util.List;

public interface DishService {

    public List<Dish> findAll();

    public Dish findById(int id);

    public void save(Dish dish);

    public void deleteById(int id);
}
