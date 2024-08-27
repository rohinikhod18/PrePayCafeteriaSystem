package com.kpit.cps.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class VendorUsersTest {

    @Test
    public void testGettersAndSetters() {
        VendorUsers vendorUsers = new VendorUsers();

        vendorUsers.setUserId(1L);
        vendorUsers.setVendorId(1L);
        assertEquals(1L, vendorUsers.getUserId());
        assertEquals(1L, vendorUsers.getVendorId());
      
    }


}
