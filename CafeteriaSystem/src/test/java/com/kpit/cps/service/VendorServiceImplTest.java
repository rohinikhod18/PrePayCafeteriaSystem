package com.kpit.cps.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import java.util.*;
import com.kpit.cps.model.MenuItem;
import com.kpit.cps.model.ServingCounter;

import com.kpit.cps.model.Users;
import com.kpit.cps.model.Vendor;
import com.kpit.cps.repository.VendorRepository;



@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class VendorServiceImplTest {

     @Mock
     VendorRepository  vendorRepository;

     @InjectMocks
     VendorServiceImpl vendorServiceImpl;
    
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

        List<Users> usersList = new ArrayList<>();
        vendor.setUsersList(usersList);

        List<ServingCounter> servingCounters = new ArrayList<>();
        vendor.setServingCounters(servingCounters);

        List<MenuItem> menuItems = new ArrayList<>();
        vendor.setMenuItems(menuItems);

        return vendor;
    }

    @Test
    public void testSaveVendor() {
        Vendor vendor = expectedVendor();
        when(vendorRepository.findByVendorName(vendor.getVendorName())).thenReturn(Optional.empty());
        when(vendorRepository.save(vendor)).thenReturn(vendor);
        Vendor savedVendor = vendorServiceImpl.saveVendor(vendor);
        assertEquals(vendor.getId(), savedVendor.getId());
        verify(vendorRepository, times(1)).findByVendorName(vendor.getVendorName());
        verify(vendorRepository, times(1)).save(vendor);
    }

    @Test
    public void testUpdateVendor() {
        Vendor existingVendor = expectedVendor();
        Vendor updatedVendor = expectedVendor();
        updatedVendor.setVendorName("Updated Haldiram");
        updatedVendor.setContactFirstName("Updated Mukesh");
        when(vendorRepository.findById(existingVendor.getId())).thenReturn(Optional.of(existingVendor));
        when(vendorRepository.save(updatedVendor)).thenReturn(updatedVendor);
        Vendor result = vendorServiceImpl.updateVendor(updatedVendor);
        assertEquals(updatedVendor.getVendorName(), result.getVendorName());
        assertEquals(updatedVendor.getContactFirstName(), result.getContactFirstName());
        verify(vendorRepository, times(1)).findById(existingVendor.getId());
        verify(vendorRepository, times(1)).save(updatedVendor);
    }

    @Test
    public void testGetVendorById() {
        Vendor vendor = expectedVendor();
        long id = 1L;
        when(vendorRepository.findById(id)).thenReturn(Optional.of(vendor));
        Optional<Vendor> result = vendorServiceImpl.getVendorById(id);
        assertTrue(result.isPresent());
        assertEquals(vendor.getId(), result.get().getId());
        verify(vendorRepository, times(1)).findById(id);
    }

    @Test
    public void testGetAllVendors() {
        List<Vendor> vendors = new ArrayList<>();
        vendors.add(expectedVendor());
        when(vendorRepository.findAll()).thenReturn(vendors);
        List<Vendor> result = vendorServiceImpl.getAllVendors();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(vendorRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteVendor() {
        Vendor vendor = expectedVendor();
        long id = 1L;
        when(vendorRepository.findById(id)).thenReturn(Optional.of(vendor));
        vendorServiceImpl.deleteVendor(id);
        verify(vendorRepository, times(1)).findById(id);
        verify(vendorRepository, times(1)).deleteById(id);
    }


}
