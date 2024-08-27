package com.kpit.cps.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.kpit.cps.model.Users;
import com.kpit.cps.model.Vendor;
import com.kpit.cps.model.VendorUsers;
import com.kpit.cps.repository.UsersRepository;
import com.kpit.cps.repository.VendorRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class VendorUsersServiceImplTest {

    @Mock
    private VendorRepository vendorRepository;

    @Mock
    private UsersRepository userRepository;

    @InjectMocks
    private VendorUsersServiceImpl vendorUsersServiceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

     private VendorUsers expectedVendorUsers() {
        VendorUsers vendorUsers = new VendorUsers();
        vendorUsers.setVendorId(1L);
        vendorUsers.setUserId(2L);
        return vendorUsers;
    }

    private Vendor expectedVendor() {
        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setUsersList(new ArrayList<>());
        return vendor;
    }

    private Users expectedUser() {
        Users user = new Users();
        user.setId(2L);
        user.setVendorsList(new ArrayList<>());
        return user;
    }

    @Test
    public void testSaveVendorUsers() {
        VendorUsers vendorUsers = expectedVendorUsers();
        Vendor vendor = expectedVendor();
        Users user = expectedUser();

        when(vendorRepository.findById(eq(1L))).thenReturn(Optional.of(vendor));
        when(userRepository.findById(eq(2L))).thenReturn(Optional.of(user));
        when(vendorRepository.save(eq(vendor))).thenReturn(vendor);
        when(userRepository.save(eq(user))).thenReturn(user);

        VendorUsers result = vendorUsersServiceImpl.saveVendorUsers(vendorUsers);
        
        assertEquals(vendorUsers, result);
        verify(vendorRepository, times(1)).save(eq(vendor));
        verify(userRepository, times(1)).save(eq(user));
    }

    @Test
    public void testRemoveVendorUsers() {
        VendorUsers vendorUsers = expectedVendorUsers();
        Vendor vendor = expectedVendor();
        Users user = expectedUser();

        user.getVendorsList().add(vendor);
        vendor.getUsersList().add(user);

        when(vendorRepository.findById(eq(1L))).thenReturn(Optional.of(vendor));
        when(userRepository.findById(eq(2L))).thenReturn(Optional.of(user));
        when(vendorRepository.save(eq(vendor))).thenReturn(vendor);
        when(userRepository.save(eq(user))).thenReturn(user);

        vendorUsersServiceImpl.removeVendorUsers(vendorUsers);

        verify(vendorRepository, times(1)).save(eq(vendor));
        verify(userRepository, times(1)).save(eq(user));
    }


}
