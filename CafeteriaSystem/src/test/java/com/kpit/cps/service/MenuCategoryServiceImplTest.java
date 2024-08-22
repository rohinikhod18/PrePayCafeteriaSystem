package com.kpit.cps.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.kpit.cps.exception.DataIsNotPresentException;
import com.kpit.cps.exception.DuplicateDataException;
import com.kpit.cps.exception.IdNotFoundException;
import com.kpit.cps.model.MenuCategory;
import com.kpit.cps.repository.MenuCategoryRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MenuCategoryServiceImplTest {

    @Mock
    private MenuCategoryRepository menuCategoryRepository;

    @InjectMocks
    private MenuCategoryServiceImpl menuCategoryServiceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private MenuCategory createSampleMenuCategory() {
        MenuCategory menuCategory = new MenuCategory();
        menuCategory.setId(1L);
        menuCategory.setName("Beverages");
        menuCategory.setDescription("Drinks and Refreshments");
        return menuCategory;
    }

    
    @Test
    public void testSaveMenuCategory() {
        MenuCategory menuCategory = createSampleMenuCategory();

        when(menuCategoryRepository.findByName(menuCategory.getName())).thenReturn(Optional.empty());
        when(menuCategoryRepository.save(menuCategory)).thenReturn(menuCategory);

        MenuCategory savedCategory = menuCategoryServiceImpl.saveMenuCategory(menuCategory);

        assertEquals(menuCategory.getId(), savedCategory.getId());
        verify(menuCategoryRepository, times(1)).findByName(menuCategory.getName());
        verify(menuCategoryRepository, times(1)).save(menuCategory);
    }

    @Test
    public void testSaveMenuCategory_DuplicateName() {
        MenuCategory menuCategory = createSampleMenuCategory();

        when(menuCategoryRepository.findByName(menuCategory.getName())).thenReturn(Optional.of(menuCategory));

        assertThrows(DuplicateDataException.class, () -> {
            menuCategoryServiceImpl.saveMenuCategory(menuCategory);
        });

        verify(menuCategoryRepository, times(1)).findByName(menuCategory.getName());
        verify(menuCategoryRepository, never()).save(menuCategory);
    }

    
    @Test
    public void testGetMenuCategoryById() {
        MenuCategory menuCategory = createSampleMenuCategory();
        long id = 1;

        when(menuCategoryRepository.findById(id)).thenReturn(Optional.of(menuCategory));

        Optional<MenuCategory> result = menuCategoryServiceImpl.getMenuCategoryById(id);

        assertTrue(result.isPresent());
        assertEquals(menuCategory.getId(), result.get().getId());
        verify(menuCategoryRepository, times(1)).findById(id);
    }

    @Test
    public void testGetMenuCategoryById_NotFound() {
        long id = 2;

        when(menuCategoryRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> {
            menuCategoryServiceImpl.getMenuCategoryById(id);
        });

        verify(menuCategoryRepository, times(1)).findById(id);
    }


    @Test
    public void testGetAllMenuCategories() {
        List<MenuCategory> menuCategories = new ArrayList<>();
        menuCategories.add(createSampleMenuCategory());

        when(menuCategoryRepository.findAll()).thenReturn(menuCategories);

        List<MenuCategory> result = menuCategoryServiceImpl.getAllMenuCategories();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(menuCategoryRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllMenuCategories_NoData() {
        when(menuCategoryRepository.findAll()).thenReturn(new ArrayList<>());

        assertThrows(DataIsNotPresentException.class, () -> {
            menuCategoryServiceImpl.getAllMenuCategories();
        });

        verify(menuCategoryRepository, times(1)).findAll();
    }

    @Test
  public void testUpdateMenuCategory_Success() {
    MenuCategory existingCategory = createSampleMenuCategory();
    MenuCategory updatedCategory = createSampleMenuCategory();
    updatedCategory.setName("Updated Beverages");
    updatedCategory.setDescription("Updated drinks and refreshments");

    when(menuCategoryRepository.findById(existingCategory.getId())).thenReturn(Optional.of(existingCategory));
    when(menuCategoryRepository.save(updatedCategory)).thenReturn(updatedCategory);

    MenuCategory result = menuCategoryServiceImpl.updateMenuCategory(updatedCategory);

    assertEquals(updatedCategory.getName(), result.getName());
    assertEquals(updatedCategory.getDescription(), result.getDescription());
    verify(menuCategoryRepository, times(1)).findById(existingCategory.getId());
    verify(menuCategoryRepository, times(1)).save(updatedCategory);
}

    @Test
    public void testUpdateMenuCategory_NotFound() {
        MenuCategory updatedCategory = createSampleMenuCategory();
        updatedCategory.setId(2L); // Assume this ID does not exist in the repository

        when(menuCategoryRepository.findById(updatedCategory.getId())).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> {
            menuCategoryServiceImpl.updateMenuCategory(updatedCategory);
        });

        verify(menuCategoryRepository, times(1)).findById(updatedCategory.getId());
        verify(menuCategoryRepository, never()).save(updatedCategory);
    }


    
    @Test
    public void testDeleteMenuCategory() {
        MenuCategory menuCategory = createSampleMenuCategory();
        long id = 1;

        when(menuCategoryRepository.findById(id)).thenReturn(Optional.of(menuCategory));

        menuCategoryServiceImpl.deleteMenuCategory(id);

        verify(menuCategoryRepository, times(1)).findById(id);
        verify(menuCategoryRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteMenuCategory_NotFound() {
        long id = 2;

        when(menuCategoryRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> {
            menuCategoryServiceImpl.deleteMenuCategory(id);
        });

        verify(menuCategoryRepository, times(1)).findById(id);
        verify(menuCategoryRepository, never()).deleteById(id);
    }
}
