package com.kpit.cps.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kpit.cps.dto.UsersRequestDTO;
import com.kpit.cps.model.Users;
import com.kpit.cps.service.UsersService;

@RestController
@RequestMapping("/cafeteriaapi/users")
public class UsersController {

    @Autowired
    UsersService usersService;

    Logger logger = LoggerFactory.getLogger(UsersController.class);

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody UsersRequestDTO userRequestDTO){
        logger.info("Creating new user with data: {}", userRequestDTO);
        Users createdUser = usersService.saveUser(userRequestDTO);
        logger.info("Created user with ID: {}", createdUser.getId());
        return new ResponseEntity<Users>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers(){
        logger.info("Fetching all users");
        List<Users> listOfAllUsers = usersService.getAllUsers();
        logger.info("Fetched {} users", listOfAllUsers.size());
        return new ResponseEntity<List<Users>>(listOfAllUsers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Users>> getUserById(@PathVariable("id") long id){
        logger.info("Fetching user with ID: {}", id);
        Optional<Users> optionalUser = usersService.getUserById(id);
        logger.info("Fetched user: {}", optionalUser.get());
        return new ResponseEntity<Optional<Users>>(optionalUser, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Users> updateUser(@RequestBody Users user){
        logger.info("Updating user with ID: {}", user.getId());
        Users updatedUser = usersService.updateUser(user);
        logger.info("Updated user: {}", updatedUser);
        return new ResponseEntity<Users>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id){
        logger.info("Deleting user with ID: {}", id);
        usersService.deleteUser(id);
        logger.info("Deleted user with ID: {}", id);
        return new ResponseEntity<>("User Deleted Successfully", HttpStatus.OK);
    }

}
