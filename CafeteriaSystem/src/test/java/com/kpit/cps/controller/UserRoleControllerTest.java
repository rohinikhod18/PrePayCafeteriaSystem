package com.kpit.cps.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

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

import com.kpit.cps.model.UserRole;
import com.kpit.cps.service.UserRoleService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserRoleControllerTest {


    @InjectMocks
    UserRoleController userRoleController;

    @Mock
    UserRoleService userRoleService;

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
    public void testCreateUserRole() {
        UserRole userRole = expectedUserRole();
        when(userRoleService.saveUserRole(userRole)).thenReturn(userRole);

        ResponseEntity<UserRole> response = userRoleController.createUserRole(userRole);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userRole, response.getBody());
        verify(userRoleService, times(1)).saveUserRole(userRole);
    }

    @Test
    public void testGetAllUserRoles() {
        List<UserRole> userRoles = List.of(expectedUserRole());
        when(userRoleService.getAllUserRoles()).thenReturn(userRoles);

        ResponseEntity<List<UserRole>> response = userRoleController.getAllUserRoles();
        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(userRoles, response.getBody());
        verify(userRoleService, times(1)).getAllUserRoles();
    }

    @Test
    public void testGetUserRoleById() {
        long id = 1L;
        UserRole userRole = expectedUserRole();
        Optional<UserRole> optionalUserRole = Optional.of(userRole);

        when(userRoleService.getUserRoleById(id)).thenReturn(optionalUserRole);
        ResponseEntity<Optional<UserRole>> response = userRoleController.getUserRoleById(id);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(optionalUserRole, response.getBody());
    }

    @Test
    public void testUpdateUserRole() {
        UserRole userRole = expectedUserRole();
        when(userRoleService.updateUserRole(any(UserRole.class))).thenReturn(userRole);
        ResponseEntity<UserRole> response = userRoleController.updateUserRole(userRole);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody()); 
        assertEquals(userRole, response.getBody()); 
        verify(userRoleService, times(1)).updateUserRole(any(UserRole.class));
    }



    @Test
    public void testDeleteUserRole() {
        doNothing().when(userRoleService).deleteUserRole(anyLong());

        ResponseEntity<String> response = userRoleController.deleteUserRole(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User Role Deleted Succesfully", response.getBody());
        verify(userRoleService, times(1)).deleteUserRole(anyLong());
    }



    


}
