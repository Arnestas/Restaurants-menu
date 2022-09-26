package com.example.restaurant.service;

import com.example.restaurant.dao.DishRepository;
import com.example.restaurant.entity.Dish;
import com.example.restaurant.entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishServiceImpl implements DishService{

    private DishRepository dishRepository;

    @Autowired
    public DishServiceImpl(DishRepository theDishRepository) {
        dishRepository = theDishRepository;
    }

    @Override
    public List<Dish> findAll() {
        return dishRepository.findAll();
    }

    @Override
    public Dish findById(int id) {

        Optional<Dish> result = dishRepository.findById(id);

        Dish dish = null;

        if(result.isPresent()){
            dish = result.get();
        }else{
            throw new RuntimeException("There is no dish with id " + id);
        }

        return dish;
    }

    @Override
    public void save(Dish dish) {
        dishRepository.save(dish);
    }

    @Override
    public void deleteById(int id) {
        dishRepository.deleteById(id);
    }

}
