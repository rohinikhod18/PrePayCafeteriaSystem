package com.kpit.cps.service;

import java.util.*;

import com.kpit.cps.model.MenuCategory;

public interface MenuCategoryService {

   public MenuCategory saveMenuCategory(MenuCategory menuCategory);

   public  MenuCategory updateMenuCategory(MenuCategory menuCategory);

   public List<MenuCategory> getAllMenuCategories();

   public  Optional<MenuCategory> getMenuCategoryById(long id);

   public void  deleteMenuCategory(long id);

}
