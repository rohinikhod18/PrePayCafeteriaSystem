package com.kpit.cps.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpit.cps.dto.MenuItemDTO;
import com.kpit.cps.exception.DataIsNotPresentException;
import com.kpit.cps.exception.IdNotFoundException;
import com.kpit.cps.model.MenuCategory;
import com.kpit.cps.model.MenuItem;
import com.kpit.cps.model.ServingCounter;

import com.kpit.cps.model.Vendor;
import com.kpit.cps.repository.MenuCategoryRepository;
import com.kpit.cps.repository.MenuItemRepository;
import com.kpit.cps.repository.ServingCounterRepository;
import com.kpit.cps.repository.VendorRepository;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    @Autowired
    MenuItemRepository menuItemRepository;
    

    @Autowired
    ServingCounterRepository servingCounterRepository;

    @Autowired
    MenuCategoryRepository menuCategoryRepository ;

    @Autowired
    VendorRepository vendorRepository;

    @Autowired
    ModelMapper modelMapper;

    Logger logger = LoggerFactory.getLogger(MenuItemServiceImpl.class);
    

    @Override
    public MenuItem saveMenuItem(MenuItemDTO menuItemDTO) {
        MenuItem menuItem = modelMapper.map(menuItemDTO, MenuItem.class);

        Optional<MenuCategory> optionalMenuCategory = menuCategoryRepository.findById(menuItemDTO.getMenuCategoryId());
        if (optionalMenuCategory.isPresent()) {
            menuItem.setMenuCategory(optionalMenuCategory.get());
        } else {
            throw new IdNotFoundException("MenuCategory not found with ID: " + menuItemDTO.getMenuCategoryId());
        }

        Optional<Vendor> optionalVendor = vendorRepository.findById(menuItemDTO.getVendorId());
        if (optionalVendor.isPresent()) {
            menuItem.setVendor(optionalVendor.get());
        } else {
            throw new IdNotFoundException("Vendor not found with ID: " + menuItemDTO.getVendorId());
        }

        Optional<ServingCounter> optionalServingCounter = servingCounterRepository.findById(menuItemDTO.getServingCounterId());
        if (optionalServingCounter.isPresent()) {
            menuItem.setServingCounter(optionalServingCounter.get());
        } else {
            throw new IdNotFoundException("ServingCounter not found with ID: " + menuItemDTO.getServingCounterId());
        }
          MenuItem savedMenuItem = menuItemRepository.save(menuItem);
          logger.info("Saved menu item with ID: {}", savedMenuItem.getId());
          return savedMenuItem;
    }

    @Override
    public MenuItem updateMenuItem(MenuItem MenuItem) {
        logger.info("Updating menu item with ID: {}", MenuItem.getId());
        Optional<MenuItem> optionalMenuItem = menuItemRepository.findById(MenuItem.getId());
        if (!optionalMenuItem.isPresent()) {
            logger.warn("Menu item not found with ID: {}", MenuItem.getId());
            throw new IdNotFoundException("MenuItem not found with ID: " + MenuItem.getId());
        }
        MenuItem updatedMenuItem = menuItemRepository.save(MenuItem);
        logger.info("Updated menu item: {}", updatedMenuItem);
        return updatedMenuItem;
    }

    @Override
    public Optional<MenuItem> getMenuItemById(long id) {
        logger.info("Fetching menu item with ID: {}", id);
        Optional<MenuItem> optionalMenuItem = menuItemRepository.findById(id);
        if (!optionalMenuItem.isPresent()) {
            logger.warn("Menu item not found with ID: {}", id);
            throw new IdNotFoundException("MenuItem not found with ID: " + id);
        }
        logger.info("Fetched menu item: {}", optionalMenuItem.get());
        return optionalMenuItem;
    }

    @Override
    public List<MenuItem> getAllMenuiItems() {
        logger.info("Fetching all menu items");
        List<MenuItem> menuItems = menuItemRepository.findAll();
        if (menuItems.isEmpty()) {
            logger.warn("No menu items found");
            throw new DataIsNotPresentException("No menu items found");
        }
        logger.info("Fetched {} menu items", menuItems.size());
        return menuItems;  
            }

    @Override
    public void deleteMenuItem(long id) {
        logger.info("Deleting menu item with ID: {}", id);
        Optional<MenuItem> optionalMenuItem = menuItemRepository.findById(id);
        if (!optionalMenuItem.isPresent()) {
            logger.warn("Menu item not found with ID: {}", id);
            throw new IdNotFoundException("MenuItem not found with ID: " + id);
        }
        menuItemRepository.deleteById(id);
        logger.info("Deleted menu item with ID: {}", id);
    
    }

}
