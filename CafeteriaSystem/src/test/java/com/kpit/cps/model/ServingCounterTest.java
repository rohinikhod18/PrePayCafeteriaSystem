package com.kpit.cps.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ServingCounterTest {


    @Test
    public void testGettersAndSetters() {
        ServingCounter servingCounter = new ServingCounter();
        
        servingCounter.setId(1L);
        servingCounter.setName("Counter 1");
        servingCounter.setActive(true);
        servingCounter.setUpdatedBy(1L);
        servingCounter.setUpdatedOn("2024-08-23 15:00");
        servingCounter.setCreatedBy(1L);
        servingCounter.setCreatedOn("2024-08-23 14:30");
        
        Vendor vendor = new Vendor();
        vendor.setId(1L);
        servingCounter.setVendor(vendor);
        
        MenuItem menuItem1 = new MenuItem();
        menuItem1.setId(1L);
        MenuItem menuItem2 = new MenuItem();
        menuItem2.setId(2L);
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(menuItem1);
        menuItems.add(menuItem2);
        servingCounter.setMenuItems(menuItems);

        assertEquals(1L, servingCounter.getId());
        assertEquals("Counter 1", servingCounter.getName());
        assertEquals(true, servingCounter.isActive());
        assertEquals(1L, servingCounter.getUpdatedBy());
        assertEquals("2024-08-23 15:00", servingCounter.getUpdatedOn());
        assertEquals(1L, servingCounter.getCreatedBy());
        assertEquals("2024-08-23 14:30", servingCounter.getCreatedOn());
        assertEquals(vendor, servingCounter.getVendor());
        assertEquals(2, servingCounter.getMenuItems().size());
        assertEquals(menuItem1, servingCounter.getMenuItems().get(0));
        assertEquals(menuItem2, servingCounter.getMenuItems().get(1));
    }
}
