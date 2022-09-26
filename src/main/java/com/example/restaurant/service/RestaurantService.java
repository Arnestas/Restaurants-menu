package com.example.restaurant.service;

import com.example.restaurant.entity.Restaurant;

import java.util.List;

public interface RestaurantService {

    public List<Restaurant> findAll();

    public Restaurant findById(int id);

    public void save(Restaurant restaurant);

    public void deleteById(int id);
}
