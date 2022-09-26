package com.example.restaurant.service;

import com.example.restaurant.dao.MenuRepository;
import com.example.restaurant.entity.Menu;
import com.example.restaurant.entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuServiceimpl implements MenuService{

    private MenuRepository menuRepository;

    @Autowired
    public MenuServiceimpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public List<Menu> findAll() {

        return menuRepository.findAll();
    }

    @Override
    public Menu findById(int id) {

        Optional<Menu> result = menuRepository.findById(id);

        Menu menu = null;

        if(result.isPresent()){
            menu = result.get();
        } else {
            throw new RuntimeException("Did not find restaurant id " + id);
        }

        return menu;
    }

    @Override
    public void save(Menu menu) {
        menuRepository.save(menu);
    }

    @Override
    public void deleteById(int id) {
        menuRepository.deleteById(id);
    }

}
