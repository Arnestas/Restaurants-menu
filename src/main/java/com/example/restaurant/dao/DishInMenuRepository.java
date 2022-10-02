package com.example.restaurant.dao;

import com.example.restaurant.entity.Dish;
import com.example.restaurant.entity.DishInMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishInMenuRepository extends JpaRepository<DishInMenu, Integer> {

}
