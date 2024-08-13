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

import com.kpit.cps.model.MenuCategory;
import com.kpit.cps.service.MenuCategoryService;

@RestController
@RequestMapping("/cafeteriaapi/menucategory")
public class MenuCategoryController {

     @Autowired
    MenuCategoryService menuCategoryService;

    Logger logger = LoggerFactory.getLogger(MenuCategoryController.class);

    @PostMapping
    public ResponseEntity<MenuCategory> createMenuCategory(@RequestBody MenuCategory menuCategory) {
        logger.info("Creating new menu category with data: {}", menuCategory);
        MenuCategory createdMenuCategory = menuCategoryService.saveMenuCategory(menuCategory);
        logger.info("Created menu category with ID: {}", createdMenuCategory.getId());
        return new ResponseEntity<>(createdMenuCategory, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MenuCategory>> getAllMenuCategories() {
        logger.info("Fetching all menu categories");
        List<MenuCategory> listOfAllMenuCategories = menuCategoryService.getAllMenuCategories();
        logger.info("Fetched {} menu categories", listOfAllMenuCategories.size());
        return new ResponseEntity<>(listOfAllMenuCategories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<MenuCategory>> getMenuCategoryById(@PathVariable("id") long id) {
        logger.info("Fetching menu category with ID: {}", id);
        Optional<MenuCategory> optionalMenuCategory = menuCategoryService.getMenuCategoryById(id);
        logger.info("Fetched menu category: {}", optionalMenuCategory.get());
        return new ResponseEntity<>(optionalMenuCategory, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<MenuCategory> updateMenuCategory(@RequestBody MenuCategory menuCategory) {
        logger.info("Updating menu category with ID: {}", menuCategory.getId());
        MenuCategory updatedMenuCategory = menuCategoryService.updateMenuCategory(menuCategory);
        logger.info("Updated menu category: {}", updatedMenuCategory);
        return new ResponseEntity<>(updatedMenuCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMenuCategory(@PathVariable("id") long id) {
        logger.info("Deleting menu category with ID: {}", id);
        menuCategoryService.deleteMenuCategory(id);
        logger.info("Deleted menu category with ID: {}", id);
        return new ResponseEntity<>("Menu category deleted successfully", HttpStatus.OK);
    }

}
