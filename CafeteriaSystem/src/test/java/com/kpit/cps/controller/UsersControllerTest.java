package com.kpit.cps.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

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

import com.kpit.cps.dto.UsersRequestDTO;

import com.kpit.cps.model.Users;

import com.kpit.cps.service.UsersService;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UsersControllerTest {

    @Mock
     UsersService usersService;

    @InjectMocks
    UsersController usersController;


     @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

     
    private Users expectedUser() {
        Users user = new Users();
        user.setId(1L);
        user.setUserName("Virat Kohli");
        user.setDevice(12345);
        user.setEmail("viratkohli@gmail.com");
        user.setPhoneNumber("1234567890");
        user.setActive(true);
        user.setCreatedBy(1L);
        user.setCreatedOn("2024-08-25");
        user.setUpdatedBy(2L);
        user.setUpdatedOn("2024-08-26");
        return user;
    }

    private UsersRequestDTO expectedUserRequestDTO() {
        UsersRequestDTO userRequestDTO = new UsersRequestDTO();
        userRequestDTO.setId(1L);
        userRequestDTO.setUserName("Virat Kohli");
        userRequestDTO.setDevice(12345);
        userRequestDTO.setEmail("viratkohli@gmail.com");
        userRequestDTO.setPhoneNumber("1234567890");
        userRequestDTO.setActive(true);
        userRequestDTO.setCreatedBy(1L);
        userRequestDTO.setCreatedOn("2024-08-25");
        userRequestDTO.setUpdatedBy(2L);
        userRequestDTO.setUpdatedOn("2024-08-26");
        return userRequestDTO;
    }

    @Test
    public void testCreateUser() {
        UsersRequestDTO userRequestDTO = expectedUserRequestDTO();
        Users user = expectedUser();
        when(usersService.saveUser(any(UsersRequestDTO.class))).thenReturn(user);

        ResponseEntity<Users> response = usersController.createUser(userRequestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(usersService, times(1)).saveUser(any(UsersRequestDTO.class));
    }

    @Test
    public void testGetAllUsers() {
        List<Users> usersList = List.of(expectedUser());
        when(usersService.getAllUsers()).thenReturn(usersList);

        ResponseEntity<List<Users>> response = usersController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usersList, response.getBody());
        verify(usersService, times(1)).getAllUsers();
    }

    @Test
    public void testGetUserById() {
        long id = 1L;
        Users user = expectedUser();
        Optional<Users> optionalUser = Optional.of(user);

        when(usersService.getUserById(id)).thenReturn(optionalUser);
        ResponseEntity<Optional<Users>> response = usersController.getUserById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(optionalUser, response.getBody());
        verify(usersService, times(1)).getUserById(id);
    }

    @Test
    public void testUpdateUser() {
        Users user = expectedUser();
        when(usersService.updateUser(any(Users.class))).thenReturn(user);

        ResponseEntity<Users> response = usersController.updateUser(user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(user, response.getBody());
        verify(usersService, times(1)).updateUser(any(Users.class));
    }

    @Test
    public void testDeleteUser() {
        long id = 1L;
        doNothing().when(usersService).deleteUser(id);

        ResponseEntity<String> response = usersController.deleteUser(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User Deleted Successfully", response.getBody());
        verify(usersService, times(1)).deleteUser(id);
    }

}
