package com.kpit.cps.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MenuItemTest {


    @Test
    public void testGettersAndSetters() {
        MenuItem menuItem = new MenuItem();
        menuItem.setId(1L);
        menuItem.setName("Pizza");
        menuItem.setPrice(250L);
        menuItem.setDescription("Delicious cheese pizza");
        menuItem.setAvailable('Y');
        menuItem.setVegFlag('Y');
        menuItem.setActive('Y');

        MenuCategory menuCategory = new MenuCategory();
        menuItem.setMenuCategory(menuCategory);

        Vendor vendor = new Vendor();
        menuItem.setVendor(vendor);

        ServingCounter servingCounter = new ServingCounter();
        menuItem.setServingCounter(servingCounter);

        menuItem.setCreatedBy(1L);
        menuItem.setCreatedOn("2024-08-29");
        menuItem.setUpdatedBy(1L);
        menuItem.setUpdatedOn("2024-08-30");

        List<OrderItems> orderItems = new ArrayList<>();
        menuItem.setOrderItems(orderItems);

        // Assertions
        assertEquals(1L, menuItem.getId());
        assertEquals("Pizza", menuItem.getName());
        assertEquals(250L, menuItem.getPrice());
        assertEquals("Delicious cheese pizza", menuItem.getDescription());
        assertEquals('Y', menuItem.getAvailable());
        assertEquals('Y', menuItem.getVegFlag());
        assertEquals('Y', menuItem.getActive());
        assertEquals(menuCategory, menuItem.getMenuCategory());
        assertEquals(vendor, menuItem.getVendor());
        assertEquals(servingCounter, menuItem.getServingCounter());
        assertEquals(1L, menuItem.getCreatedBy());
        assertEquals("2024-08-29", menuItem.getCreatedOn());
        assertEquals(1L, menuItem.getUpdatedBy());
        assertEquals("2024-08-30", menuItem.getUpdatedOn());
        assertEquals(orderItems, menuItem.getOrderItems());
    }

}
