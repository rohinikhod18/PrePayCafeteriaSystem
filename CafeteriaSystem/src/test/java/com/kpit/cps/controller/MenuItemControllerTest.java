package com.kpit.cps.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kpit.cps.dto.MenuItemDTO;

import com.kpit.cps.model.MenuItem;

import com.kpit.cps.service.MenuItemService;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MenuItemControllerTest {

     @Mock
     MenuItemService menuItemService;

    @InjectMocks
     MenuItemController menuItemController;

     private MenuItem expectedMenuItem() {
        MenuItem menuItem = new MenuItem();
        
        menuItem.setId(1L);
        menuItem.setName("Pizza");
        menuItem.setPrice(250L);
        menuItem.setDescription("Delicious cheese pizza");
        menuItem.setAvailable('Y');
        menuItem.setVegFlag('Y');
        menuItem.setActive('Y');
        menuItem.setCreatedBy(1L);
        menuItem.setCreatedOn("2024-08-29");
        menuItem.setUpdatedBy(1L);
        menuItem.setUpdatedOn("2024-08-30");

        return menuItem;
    }

    private MenuItemDTO expectedMenuItemDTO() {
        MenuItemDTO menuItemDTO = new MenuItemDTO();
        
        menuItemDTO.setId(1L);
        menuItemDTO.setName("Pizza");
        menuItemDTO.setPrice(250L);
        menuItemDTO.setDescription("Delicious cheese pizza");
        menuItemDTO.setAvailable('Y');
        menuItemDTO.setVegFlag('Y');
        menuItemDTO.setActive('Y');
        menuItemDTO.setCreatedBy(1L);
        menuItemDTO.setCreatedOn("2024-08-29");
        menuItemDTO.setUpdatedBy(1L);
        menuItemDTO.setUpdatedOn("2024-08-30");

        return menuItemDTO;
    }

    @Test
    public void testCreateMenuItem() {
        MenuItemDTO menuItemDTO = expectedMenuItemDTO();
        MenuItem menuItem = expectedMenuItem();
        
        when(menuItemService.saveMenuItem(any(MenuItemDTO.class))).thenReturn(menuItem);

        ResponseEntity<MenuItem> response = menuItemController.createMenuItem(menuItemDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(menuItem, response.getBody());
        verify(menuItemService, times(1)).saveMenuItem(any(MenuItemDTO.class));
    }

    @Test
    public void testGetMenuItemById() {
        long id = 1L;
        MenuItem menuItem = expectedMenuItem();
        Optional<MenuItem> optionalMenuItem = Optional.of(menuItem);

        when(menuItemService.getMenuItemById(id)).thenReturn(optionalMenuItem);
        ResponseEntity<Optional<MenuItem>> response = menuItemController.getMenuItemById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(optionalMenuItem, response.getBody());
        verify(menuItemService, times(1)).getMenuItemById(id);
    }

    @Test
    public void testGetAllMenuItems() {
        List<MenuItem> menuItemList = List.of(expectedMenuItem());
        when(menuItemService.getAllMenuiItems()).thenReturn(menuItemList);

        ResponseEntity<List<MenuItem>> response = menuItemController.getAllMenuItems();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(menuItemList, response.getBody());
        verify(menuItemService, times(1)).getAllMenuiItems();
    }

    @Test
    public void testUpdateMenuItem() {
        MenuItem menuItem = expectedMenuItem();
        when(menuItemService.updateMenuItem(any(MenuItem.class))).thenReturn(menuItem);

        ResponseEntity<MenuItem> response = menuItemController.updateMenuItem(menuItem);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(menuItem, response.getBody());
        verify(menuItemService, times(1)).updateMenuItem(any(MenuItem.class));
    }

    @Test
    public void testDeleteMenuItem() {
        long id = 1L;
        doNothing().when(menuItemService).deleteMenuItem(id);

        ResponseEntity<String> response = menuItemController.deleteMenuItem(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Menu Item Deleted Successfully", response.getBody());
        verify(menuItemService, times(1)).deleteMenuItem(id);
    }



}
