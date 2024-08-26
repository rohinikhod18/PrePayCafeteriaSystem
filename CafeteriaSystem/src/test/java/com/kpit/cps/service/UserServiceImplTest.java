package com.kpit.cps.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
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
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.kpit.cps.dto.UsersRequestDTO;
import com.kpit.cps.model.UserRole;
import com.kpit.cps.model.Users;
import com.kpit.cps.repository.UserRoleRepository;
import com.kpit.cps.repository.UsersRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    UsersRepository usersRepository;

    @InjectMocks
    UsersServiceImpl usersServiceImpl;

    @Mock
    ModelMapper modelMapper;

    @Mock
    UserRoleRepository userRoleRepository;
    
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
    public void testSaveUser() {
        UsersRequestDTO userRequestDTO = expectedUserRequestDTO();
        Users user = expectedUser();
        UserRole userRole = new UserRole();
        userRole.setId(1L);

        when(modelMapper.map(any(UsersRequestDTO.class), eq(Users.class))).thenReturn(user);
        when(userRoleRepository.findById(userRequestDTO.getUserRoleId())).thenReturn(Optional.of(userRole));
        when(usersRepository.save(any(Users.class))).thenReturn(user);
        Users savedUser = usersServiceImpl.saveUser(userRequestDTO);
        assertEquals(user, savedUser);
        verify(userRoleRepository, times(1)).findById(userRequestDTO.getUserRoleId());
        verify(usersRepository, times(1)).save(any(Users.class));
    }
    

    @Test
    public void testGetAllUsers() {
        List<Users> usersList = List.of(expectedUser());
        when(usersRepository.findAll()).thenReturn(usersList);
        List<Users> result = usersServiceImpl.getAllUsers();
        assertEquals(usersList.size(), result.size());
        assertEquals(usersList, result);
        verify(usersRepository, times(1)).findAll();
    }

    @Test
    public void testGetUserById() {
        long id = 1L;
        Optional<Users> optionalUser = Optional.of(expectedUser());
        when(usersRepository.findById(anyLong())).thenReturn(optionalUser);
        Optional<Users> result = usersServiceImpl.getUserById(id);
        assertTrue(result.isPresent());
        assertEquals(optionalUser.get(), result.get());
        verify(usersRepository, times(1)).findById(id);
    }

    @Test
    public void testUpdateUser() {
        Users user = expectedUser();
        when(usersRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(usersRepository.save(any(Users.class))).thenReturn(user);
        Users updatedUser = usersServiceImpl.updateUser(user);
        assertEquals(user, updatedUser);
        verify(usersRepository, times(1)).findById(user.getId());
        verify(usersRepository, times(1)).save(any(Users.class));
    }

    @Test
    public void testDeleteUser() {
        long id = 1L; 
        Users user = new Users(); 
        user.setId(id);
        when(usersRepository.findById(id)).thenReturn(Optional.of(user));
        usersServiceImpl.deleteUser(id);
        verify(usersRepository, times(1)).deleteById(id);
    }

}
