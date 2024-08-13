package com.kpit.cps.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpit.cps.exception.DataIsNotPresentException;
import com.kpit.cps.exception.DuplicateDataException;
import com.kpit.cps.exception.IdNotFoundException;
import com.kpit.cps.model.MenuCategory;
import com.kpit.cps.repository.MenuCategoryRepository;

@Service
public class MenuCategoryServiceImpl implements MenuCategoryService {

    Logger logger = LoggerFactory.getLogger(MenuCategoryServiceImpl.class);

    @Autowired
    MenuCategoryRepository menuCategoryRepository;

    @Override
    public MenuCategory saveMenuCategory(MenuCategory menuCategory) {
        logger.info("Saving menu category: {}", menuCategory);
        Optional<MenuCategory> optionalMenuCategory = menuCategoryRepository.findByName(menuCategory.getName());
        if (optionalMenuCategory.isPresent()) {
            logger.warn("Duplicate Menu Category name detected: {}", menuCategory.getName());
            throw new DuplicateDataException("Menu category is already present");
        }
        MenuCategory savedMenuCategory = menuCategoryRepository.save(menuCategory);
        logger.info("Saved menu category with ID: {}", savedMenuCategory.getId());
        return savedMenuCategory;
    }

    @Override
    public MenuCategory updateMenuCategory(MenuCategory menuCategory) {
        logger.info("Updating menu category with ID: {}", menuCategory.getId());
        Optional<MenuCategory> optionalMenuCategory = menuCategoryRepository.findById(menuCategory.getId());
        if (!optionalMenuCategory.isPresent()) {
            logger.warn("Menu category not found with ID: {}", menuCategory.getId());
            throw new IdNotFoundException("Menu category not found with ID: " + menuCategory.getId());
        }
        MenuCategory updatedMenuCategory = menuCategoryRepository.save(menuCategory);
        logger.info("Updated menu category: {}", updatedMenuCategory);
        return updatedMenuCategory;
    }

    @Override
    public List<MenuCategory> getAllMenuCategories() {
        logger.info("Fetching all menu categories");
        List<MenuCategory> menuCategories = menuCategoryRepository.findAll();
        if (menuCategories.isEmpty()) {
            logger.warn("No menu categories found");
            throw new DataIsNotPresentException("No menu categories found");
        }
        logger.info("Fetched {} menu categories", menuCategories.size());
        return menuCategories;
    }

    @Override
    public Optional<MenuCategory> getMenuCategoryById(long id) {
        logger.info("Fetching menu category with ID: {}", id);
        Optional<MenuCategory> optionalMenuCategory = menuCategoryRepository.findById(id);
        if (!optionalMenuCategory.isPresent()) {
            logger.warn("Menu category not found with ID: {}", id);
            throw new IdNotFoundException("Menu category not found with ID: " + id);
        }
        logger.info("Fetched menu category: {}", optionalMenuCategory.get());
        return optionalMenuCategory;
    }

    @Override
    public void deleteMenuCategory(long id) {
        logger.info("Deleting menu category with ID: {}", id);
        Optional<MenuCategory> optionalMenuCategory = menuCategoryRepository.findById(id);
        if (!optionalMenuCategory.isPresent()) {
            logger.warn("Menu category not found with ID: {}", id);
            throw new IdNotFoundException("Menu category not found with ID: " + id);
        }
        menuCategoryRepository.deleteById(id);
        logger.info("Deleted menu category with ID: {}", id);
    }

}
