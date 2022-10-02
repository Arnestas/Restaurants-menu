package com.example.restaurant.service;

import com.example.restaurant.dao.DishInMenuRepository;
import com.example.restaurant.dao.DishRepository;
import com.example.restaurant.entity.Dish;
import com.example.restaurant.entity.DishInMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishInMenuServiceImpl implements DishInMenuService{

    private DishInMenuRepository dishInMenuRepository;

    @Autowired
    public DishInMenuServiceImpl(DishInMenuRepository theDishInMenuRepository) {
        dishInMenuRepository = theDishInMenuRepository;
    }

    @Override
    public List<DishInMenu> findAll() {
        return dishInMenuRepository.findAll();
    }

    @Override
    public DishInMenu findById(int id) {

        Optional<DishInMenu> result = dishInMenuRepository.findById(id);

        DishInMenu dishInMenu = null;

        if(result.isPresent()){
            dishInMenu = result.get();
        }else{
            throw new RuntimeException("There is no dish in menu with id " + id);
        }

        return dishInMenu;
    }

    @Override
    public void save(DishInMenu dishInMenu) {
        dishInMenuRepository.save(dishInMenu);
    }

    @Override
    public void deleteById(int id) {
        dishInMenuRepository.deleteById(id);
    }
}
