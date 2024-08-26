package com.kpit.cps.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class VendorTest {

     @Test
    public void testGettersAndSetters() {
        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setCompanyId(1001L);
        vendor.setVendorName("Haldiram");
        vendor.setContactFirstName("Mukesh");
        vendor.setContactLastName("Agarwal");
        vendor.setContactEmail("mukesh.agarwal@example.com");
        vendor.setContactMobile(1234567890L);
        vendor.setIsDesigned("Y");
        vendor.setActive(true);
        vendor.setCreatedBy(1L);
        vendor.setCreatedOn("2024-01-01");
        vendor.setUpdatedBy(2L);
        vendor.setUpdatedOn("2024-02-01");

        List<Users> usersList = new ArrayList<>();
        vendor.setUsersList(usersList);

        List<ServingCounter> servingCounters = new ArrayList<>();
        vendor.setServingCounters(servingCounters);

        List<MenuItem> menuItems = new ArrayList<>();
        vendor.setMenuItems(menuItems);

        assertEquals(1L, vendor.getId());
        assertEquals(1001L, vendor.getCompanyId());
        assertEquals("Haldiram", vendor.getVendorName());
        assertEquals("Mukesh", vendor.getContactFirstName());
        assertEquals("Agarwal", vendor.getContactLastName());
        assertEquals("mukesh.agarwal@example.com", vendor.getContactEmail());
        assertEquals(1234567890L, vendor.getContactMobile());
        assertEquals("Y", vendor.getIsDesigned());
        assertEquals(true, vendor.isActive());
        assertEquals(1L, vendor.getCreatedBy());
        assertEquals("2024-01-01", vendor.getCreatedOn());
        assertEquals(2L, vendor.getUpdatedBy());
        assertEquals("2024-02-01", vendor.getUpdatedOn());
        assertEquals(usersList, vendor.getUsersList());
        assertEquals(servingCounters, vendor.getServingCounters());
        assertEquals(menuItems, vendor.getMenuItems());

    }

}
