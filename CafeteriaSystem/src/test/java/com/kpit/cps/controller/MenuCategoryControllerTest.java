package com.kpit.cps.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kpit.cps.model.MenuCategory;
import com.kpit.cps.service.MenuCategoryService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MenuCategoryControllerTest {

    @Mock
    MenuCategoryService menuCategoryService;

    @InjectMocks
    MenuCategoryController menuCategoryController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private MenuCategory expectedMenuCategory() {
        MenuCategory menuCategory = new MenuCategory();
        menuCategory.setId(1L);
        menuCategory.setName("Category1");
        menuCategory.setDescription("Description1");
        menuCategory.setCreatedBy(1L);
        menuCategory.setCreatedOn("2024-01-01");
        menuCategory.setUpdatedBy(1L);
        menuCategory.setUpdatedOn("2024-01-01");
        return menuCategory;
    }

    @Test
    public void testCreateMenuCategory() {
        MenuCategory menuCategory = expectedMenuCategory();
        when(menuCategoryService.saveMenuCategory(menuCategory)).thenReturn(menuCategory);

        ResponseEntity<MenuCategory> response = menuCategoryController.createMenuCategory(menuCategory);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(menuCategory, response.getBody());
        verify(menuCategoryService, times(1)).saveMenuCategory(any(MenuCategory.class));
    }

    @Test
    public void testGetAllMenuCategories() {
        List<MenuCategory> menuCategories = List.of(expectedMenuCategory());
        when(menuCategoryService.getAllMenuCategories()).thenReturn(menuCategories);

        ResponseEntity<List<MenuCategory>> response = menuCategoryController.getAllMenuCategories();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(menuCategories, response.getBody());
        verify(menuCategoryService, times(1)).getAllMenuCategories();
    }

    @Test
    public void testGetMenuCategoryById() {
        long id = 1L;
        MenuCategory expectedMenuCategory = expectedMenuCategory();
        Optional<MenuCategory> optionalMenuCategory = Optional.of(expectedMenuCategory);

        when(menuCategoryService.getMenuCategoryById(id)).thenReturn(optionalMenuCategory);
        ResponseEntity<Optional<MenuCategory>> actualResponseEntity = menuCategoryController.getMenuCategoryById(id);

        assertEquals(HttpStatus.OK, actualResponseEntity.getStatusCode()); 
        assertEquals(optionalMenuCategory, actualResponseEntity.getBody());
    }

    @Test
    public void testUpdateMenuCategory() {
        MenuCategory menuCategory = expectedMenuCategory();
        when(menuCategoryService.updateMenuCategory(menuCategory)).thenReturn(menuCategory);

        ResponseEntity<MenuCategory> response = menuCategoryController.updateMenuCategory(menuCategory);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(menuCategory, response.getBody());
        verify(menuCategoryService, times(1)).updateMenuCategory(any(MenuCategory.class));
    }

    @Test
    public void testDeleteMenuCategory() {
        doNothing().when(menuCategoryService).deleteMenuCategory(anyLong());

        ResponseEntity<String> response = menuCategoryController.deleteMenuCategory(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Menu category deleted successfully", response.getBody());
        verify(menuCategoryService, times(1)).deleteMenuCategory(anyLong());
    }



}
