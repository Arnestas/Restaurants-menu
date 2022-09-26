package com.example.restaurant.dao;

import com.example.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

   //public List<Restaurant> findAllOrderByName();
}
