package com.kpit.cps.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.kpit.cps.dto.MenuItemDTO;
import com.kpit.cps.model.MenuCategory;
import com.kpit.cps.model.MenuItem;
import com.kpit.cps.model.ServingCounter;
import com.kpit.cps.model.Vendor;
import com.kpit.cps.repository.MenuCategoryRepository;
import com.kpit.cps.repository.MenuItemRepository;
import com.kpit.cps.repository.ServingCounterRepository;
import com.kpit.cps.repository.VendorRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MenuItemServiceItemTest {

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private ServingCounterRepository servingCounterRepository;

    @Mock
    private MenuCategoryRepository menuCategoryRepository;

    @Mock
    private VendorRepository vendorRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private MenuItemServiceImpl menuItemServiceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private MenuItem expectedMenuItem() {
        MenuItem menuItem = new MenuItem();
        MenuCategory menuCategory = new MenuCategory();
        Vendor vendor = new Vendor();
        ServingCounter servingCounter = new ServingCounter();
        menuItem.setId(1L);
        menuItem.setName("Menu Item 1");
        menuItem.setMenuCategory(menuCategory);
        menuItem.setVendor(vendor);
        menuItem.setServingCounter(servingCounter);
        return menuItem;
    }

    private MenuItemDTO expectedMenuItemDTO() {
        MenuItemDTO menuItemDTO = new MenuItemDTO();
        menuItemDTO.setId(1L);
        menuItemDTO.setName("Menu Item 1");
        menuItemDTO.setMenuCategoryId(1L);
        menuItemDTO.setVendorId(1L);
        menuItemDTO.setServingCounterId(1L);
        return menuItemDTO;
    }

    @Test
    public void testSaveMenuItem() {
        MenuItemDTO menuItemDTO = expectedMenuItemDTO();
        MenuItem menuItem = expectedMenuItem();
        when(modelMapper.map(menuItemDTO, MenuItem.class)).thenReturn(menuItem);
        when(menuCategoryRepository.findById(menuItemDTO.getMenuCategoryId())).thenReturn(Optional.of(menuItem.getMenuCategory()));
        when(vendorRepository.findById(menuItemDTO.getVendorId())).thenReturn(Optional.of(menuItem.getVendor()));
        when(servingCounterRepository.findById(menuItemDTO.getServingCounterId())).thenReturn(Optional.of(menuItem.getServingCounter()));
        when(menuItemRepository.save(menuItem)).thenReturn(menuItem);
        MenuItem savedMenuItem = menuItemServiceImpl.saveMenuItem(menuItemDTO);
        assertEquals(menuItem.getId(), savedMenuItem.getId());
        assertEquals(menuItem.getName(), savedMenuItem.getName());
    }

    @Test
    public void testUpdateMenuItem() {
        MenuItem menuItem = expectedMenuItem();
        when(menuItemRepository.findById(menuItem.getId())).thenReturn(Optional.of(menuItem));
        when(menuItemRepository.save(menuItem)).thenReturn(menuItem);

        MenuItem updatedMenuItem = menuItemServiceImpl.updateMenuItem(menuItem);
        assertEquals(menuItem.getId(), updatedMenuItem.getId());
        assertEquals(menuItem.getName(), updatedMenuItem.getName());
    }

    @Test
    public void testGetAllMenuItems() {
        List<MenuItem> menuItemsList = List.of(expectedMenuItem());
        when(menuItemRepository.findAll()).thenReturn(menuItemsList);
        List<MenuItem> foundMenuItemsList = menuItemServiceImpl.getAllMenuiItems();
        assertEquals(menuItemsList.size(), foundMenuItemsList.size());
        assertEquals(menuItemsList.get(0).getId(), foundMenuItemsList.get(0).getId());
    }

    @Test
    public void testGetMenuItemById() {
        MenuItem menuItem = expectedMenuItem();
        when(menuItemRepository.findById(menuItem.getId())).thenReturn(Optional.of(menuItem));
        Optional<MenuItem> foundMenuItem = menuItemServiceImpl.getMenuItemById(menuItem.getId());
        assertEquals(menuItem.getId(), foundMenuItem.get().getId());
        assertEquals(menuItem.getName(), foundMenuItem.get().getName());
    }

    @Test
    public void testDeleteMenuItem() {
        long id = 1L;
        MenuItem menuItem = expectedMenuItem();
        when(menuItemRepository.findById(id)).thenReturn(Optional.of(menuItem));
        menuItemServiceImpl.deleteMenuItem(id);
        verify(menuItemRepository).deleteById(id);
    }

 
   




}
