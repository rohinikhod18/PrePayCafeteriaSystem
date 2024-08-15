package com.kpit.cps.service;

import java.util.List;
import java.util.Optional;

import com.kpit.cps.dto.MenuItemDTO;
import com.kpit.cps.model.MenuItem;


public interface MenuItemService {

   public MenuItem saveMenuItem(MenuItemDTO menuItemDTO);

   public MenuItem updateMenuItem(MenuItem MenuItem);

   public Optional<MenuItem> getMenuItemById(long id);

   public List<MenuItem> getAllMenuiItems();

   public void deleteMenuItem(long id);

}
