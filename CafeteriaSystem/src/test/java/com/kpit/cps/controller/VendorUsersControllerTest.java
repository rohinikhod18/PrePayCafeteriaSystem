package com.kpit.cps.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kpit.cps.model.VendorUsers;
import com.kpit.cps.service.VendorUsersService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class VendorUsersControllerTest {

    @Mock
    VendorUsersService vendorUsersService;

    @InjectMocks
    VendorUsersController vendorUsersController;

    private VendorUsers expectedVendorUsers() {
        VendorUsers vendorUsers = new VendorUsers();

        vendorUsers.setUserId(1L);
        vendorUsers.setVendorId(1L); 
        return vendorUsers;
    }

    @Test
    public void testCreateVendorUsers() {
        VendorUsers vendorUsers = expectedVendorUsers();
        when(vendorUsersService.saveVendorUsers(vendorUsers)).thenReturn(vendorUsers);

        ResponseEntity<VendorUsers> response = vendorUsersController.createVendorUsers(vendorUsers);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(vendorUsers, response.getBody());
        verify(vendorUsersService, times(1)).saveVendorUsers(any(VendorUsers.class));
    }

    @Test
    public void testRemoveVendorUsers() {
        VendorUsers vendorUsers = expectedVendorUsers();
        doNothing().when(vendorUsersService).removeVendorUsers(vendorUsers);

        ResponseEntity<String> response = vendorUsersController.removeVendorUsers(vendorUsers);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("VendorUsers Deleted Succefully", response.getBody());
        verify(vendorUsersService, times(1)).removeVendorUsers(any(VendorUsers.class));
    }


}
