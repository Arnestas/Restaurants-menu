package com.example.restaurant.service;

import com.example.restaurant.entity.Menu;

import java.util.List;

public interface MenuService {

    public List<Menu> findAll();

    public Menu findById(int id);

    public void save(Menu menu);

    public void deleteById(int id);
}
