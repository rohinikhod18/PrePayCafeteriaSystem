package com.kpit.cps.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UsersTest {

     @Test
    public void testUsersSettersAndGetters() {
        Users user = new Users();
        UserRole role = new UserRole();
        List<Vendor> vendors = new ArrayList<>();
        Vendor vendor = new Vendor();
        vendors.add(vendor);

        user.setId(1L);
        user.setUserName("Virat Kohli");
        user.setUserRole(role);
        user.setDevice(12345);
        user.setEmail("viratkohli@gmail.com");
        user.setPhoneNumber("1234567890");
        user.setActive(true);
        user.setCreatedBy(1L);
        user.setCreatedOn("2024-08-25");
        user.setUpdatedBy(2L);
        user.setUpdatedOn("2024-08-26");
        user.setVendorsList(vendors);

        assertEquals(1L, user.getId());
        assertEquals("Virat Kohli", user.getUserName());
        assertEquals(role, user.getUserRole());
        assertEquals(12345, user.getDevice());
        assertEquals("viratkohli@gmail.com", user.getEmail());
        assertEquals("1234567890", user.getPhoneNumber());
        assertTrue(user.isActive());
        assertEquals(1L, user.getCreatedBy());
        assertEquals("2024-08-25", user.getCreatedOn());
        assertEquals(2L, user.getUpdatedBy());
        assertEquals("2024-08-26", user.getUpdatedOn());
        assertNotNull(user.getVendorsList());
        assertFalse(user.getVendorsList().isEmpty());
        assertEquals(1, user.getVendorsList().size());
        assertEquals(vendor, user.getVendorsList().get(0));
    }

}
