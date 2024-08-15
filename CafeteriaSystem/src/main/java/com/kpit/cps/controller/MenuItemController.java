package com.kpit.cps.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kpit.cps.dto.MenuItemDTO;
import com.kpit.cps.model.MenuItem;
import com.kpit.cps.service.MenuItemService;

@RestController
@RequestMapping("/cafeteriaapi/menuitem")
public class MenuItemController {
      
    Logger logger= LoggerFactory.getLogger(MenuItemController.class);
    
    @Autowired
    MenuItemService menuItemService;

    @PostMapping
    public ResponseEntity<MenuItem> createMenuItem(@RequestBody MenuItemDTO menuItemDTO) {
        logger.info("Creating new menu item with data: {}", menuItemDTO);
        MenuItem createdMenuItem = menuItemService.saveMenuItem(menuItemDTO);
        logger.info("Created menu item with ID: {}", createdMenuItem.getId());
        return new ResponseEntity<>(createdMenuItem, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<MenuItem>> getMenuItemById(@PathVariable("id") long id) {
        logger.info("Fetching menu item with ID: {}", id);
        Optional<MenuItem> optionalMenuItem = menuItemService.getMenuItemById(id);
        if (optionalMenuItem.isPresent()) {
            logger.info("Fetched menu item: {}", optionalMenuItem.get());
            return new ResponseEntity<>(optionalMenuItem, HttpStatus.OK);
        } else {
            logger.warn("Menu item not found with ID: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        logger.info("Fetching all menu items");
        List<MenuItem> menuItems = menuItemService.getAllMenuiItems();
        return new ResponseEntity<>(menuItems, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<MenuItem> updateMenuItem(@RequestBody MenuItem menuItem) {
        logger.info("Updating menu item with ID: {}", menuItem.getId());
        MenuItem updatedMenuItem = menuItemService.updateMenuItem(menuItem);
        logger.info("Updated menu item: {}", updatedMenuItem);
        return new ResponseEntity<>(updatedMenuItem, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMenuItem(@PathVariable("id") long id) {
        logger.info("Deleting menu item with ID: {}", id);
        menuItemService.deleteMenuItem(id);
        logger.info("Deleted menu item with ID: {}", id);
        return new ResponseEntity<>("Menu Item Deleted Successfully", HttpStatus.OK);
    }
}
