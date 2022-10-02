package com.example.restaurant.entity;

import javax.persistence.*;

@Entity
@Table(name = "dish_in_menu")
public class DishInMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "menu_id")
    private String menuId;

    @Column(name = "dish_id")
    private String dishId;

    public DishInMenu() {
    }

    public DishInMenu(int id, String menuId, String dishId) {
        this.id = id;
        this.menuId = menuId;
        this.dishId = dishId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getDishId() {
        return dishId;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId;
    }

}
