package com.kpit.cps.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.kpit.cps.model.UserRole;
import com.kpit.cps.repository.UserRoleRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserRoleServiceImplTest {

    @Mock
    UserRoleRepository userRoleRepository;

    @InjectMocks
    UserRoleServiceImpl userRoleServiceImpl;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private UserRole expectedUserRole() {
        UserRole userRole = new UserRole();
        userRole.setId(1L);
        userRole.setRoleName("Admin");
        userRole.setIsDesigned("Y");
        userRole.setCreatedBy(1);
        userRole.setCreatedOn("2024-01-01");
        userRole.setUpdatedBy(1);
        userRole.setUpdatedOn("2024-01-01");
        return userRole;
    }


    @Test
    public void testSaveUserRole() {
        UserRole userRole = expectedUserRole();

        when(userRoleRepository.findByRoleName(userRole.getRoleName())).thenReturn(Optional.empty());
        when(userRoleRepository.save(userRole)).thenReturn(userRole);
        UserRole savedRole = userRoleServiceImpl.saveUserRole(userRole);

        assertEquals(userRole.getId(), savedRole.getId());
        verify(userRoleRepository, times(1)).findByRoleName(userRole.getRoleName());
        verify(userRoleRepository, times(1)).save(userRole);
    }

    @Test
    public void testUpdateUserRole() {
        UserRole existingRole = expectedUserRole();
        UserRole updatedRole = expectedUserRole();
        updatedRole.setRoleName("Updated Admin");
        updatedRole.setIsDesigned("N");

        when(userRoleRepository.findById(existingRole.getId())).thenReturn(Optional.of(existingRole));
        when(userRoleRepository.save(updatedRole)).thenReturn(updatedRole);

        UserRole result = userRoleServiceImpl.updateUserRole(updatedRole);
        assertEquals(updatedRole.getRoleName(), result.getRoleName());
        assertEquals(updatedRole.getIsDesigned(), result.getIsDesigned());
        verify(userRoleRepository, times(1)).findById(existingRole.getId());
        verify(userRoleRepository, times(1)).save(updatedRole);
    }

    @Test
    public void testGetUserRoleById() {
        UserRole userRole = expectedUserRole();
        long id = 1;

        when(userRoleRepository.findById(id)).thenReturn(Optional.of(userRole));

        Optional<UserRole> result = userRoleServiceImpl.getUserRoleById(id);

        assertTrue(result.isPresent());
        assertEquals(userRole.getId(), result.get().getId());
        verify(userRoleRepository, times(1)).findById(id);
    }

    @Test
    public void testGetAllUserRoles() {
        List<UserRole> userRoles = new ArrayList<>();
        userRoles.add(expectedUserRole());
        when(userRoleRepository.findAll()).thenReturn(userRoles);
        List<UserRole> result = userRoleServiceImpl.getAllUserRoles();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(userRoleRepository, times(1)).findAll();
    }



    @Test
    public void testDeleteUserRole() {
        UserRole userRole = expectedUserRole();
        long id = 1;
        when(userRoleRepository.findById(id)).thenReturn(Optional.of(userRole));
        userRoleServiceImpl.deleteUserRole(id);
        verify(userRoleRepository, times(1)).findById(id);
        verify(userRoleRepository, times(1)).deleteById(id);

    }


}
