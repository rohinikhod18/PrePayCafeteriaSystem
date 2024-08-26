package com.kpit.cps.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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


import com.kpit.cps.model.Vendor;
import com.kpit.cps.service.VendorService;

import java.util.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class VendorControllerTest {


    @Mock
    VendorService vendorService;

    @InjectMocks
    VendorController vendorController;


     @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private Vendor expectedVendor() {
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
        return vendor;
    }

    @Test
    public void testCreateVendor() {
        Vendor vendor = expectedVendor();
        when(vendorService.saveVendor(vendor)).thenReturn(vendor);

        ResponseEntity<Vendor> response = vendorController.createVendor(vendor);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(vendor, response.getBody());
        verify(vendorService, times(1)).saveVendor(vendor);
    }

    @Test
    public void testGetAllVendors() {
        List<Vendor> vendors = List.of(expectedVendor());
        when(vendorService.getAllVendors()).thenReturn(vendors);

        ResponseEntity<List<Vendor>> response = vendorController.getAllVendors();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(vendors, response.getBody());
        verify(vendorService, times(1)).getAllVendors();
    }

    @Test
    public void testGetVendorById() {
        long id = 1L;
        Vendor vendor = expectedVendor();
        Optional<Vendor> optionalVendor = Optional.of(vendor);

        when(vendorService.getVendorById(id)).thenReturn(optionalVendor);
        ResponseEntity<Optional<Vendor>> response = vendorController.getVendorById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(optionalVendor, response.getBody());
    }

    @Test
    public void testUpdateVendor() {
        Vendor vendor = expectedVendor();
        when(vendorService.updateVendor(any(Vendor.class))).thenReturn(vendor);
        ResponseEntity<Vendor> response = vendorController.updateVendor(vendor);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(vendor, response.getBody());
        verify(vendorService, times(1)).updateVendor(any(Vendor.class));
    }

    @Test
    public void testDeleteVendor() {
        doNothing().when(vendorService).deleteVendor(anyLong());

        ResponseEntity<String> response = vendorController.deleteVendor(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Vendor Deleted Successfully", response.getBody());
        verify(vendorService, times(1)).deleteVendor(anyLong());
    }


    
       

}
