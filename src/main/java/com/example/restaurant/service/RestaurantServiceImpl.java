package com.example.restaurant.service;

import com.example.restaurant.dao.RestaurantRepository;
import com.example.restaurant.entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService{

    private RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository theRestaurantRepository){
        restaurantRepository = theRestaurantRepository;
    }


    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant findById(int id) {

        Optional<Restaurant> result = restaurantRepository.findById(id);

        Restaurant restaurant = null;

        if(result.isPresent()){
            restaurant = result.get();
        } else {
            throw new RuntimeException("Did not find restaurant id " + id);
        }

        return restaurant;
    }

    @Override
    public void save(Restaurant restaurant) {
        restaurantRepository.save(restaurant);

    }

    @Override
    public void deleteById(int id) {
        restaurantRepository.deleteById(id);

    }
}
