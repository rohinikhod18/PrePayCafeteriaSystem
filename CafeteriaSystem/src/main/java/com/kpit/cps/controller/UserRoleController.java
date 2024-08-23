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

import com.kpit.cps.model.UserRole;
import com.kpit.cps.service.UserRoleService;

@RestController
@RequestMapping("/cafeteriaapi/userrole")
public class UserRoleController {
      
      @Autowired
      UserRoleService userRoleService;
      
      Logger logger=LoggerFactory.getLogger(UserRoleController.class);

      @PostMapping
      public ResponseEntity<UserRole> createUserRole(@RequestBody UserRole userRole){
         logger.info("Creating new user role with data: {}", userRole);
         UserRole createdUserRole=userRoleService.saveUserRole(userRole);
         logger.info("Created user role with ID: {}", createdUserRole.getId());
         return new ResponseEntity<UserRole>(createdUserRole,HttpStatus.CREATED);
        }
      
      @GetMapping
      public ResponseEntity<List<UserRole>> getAllUserRoles(){
         logger.info("Fetching all user roles");
         List<UserRole> listOfAllUserRoles=userRoleService.getAllUserRoles();
         logger.info("Fetched {} user roles", listOfAllUserRoles.size());
         return new ResponseEntity<List<UserRole>>(listOfAllUserRoles,HttpStatus.FOUND);
        }
      
      @GetMapping("/{id}")
      public ResponseEntity<Optional<UserRole>> getUserRoleById(@PathVariable("id")long id){
            logger.info("Fetching user role with ID: {}", id);
            Optional<UserRole>optionalUserRole=userRoleService.getUserRoleById(id);
            logger.info("Fetched user role: ");
            return new ResponseEntity<Optional<UserRole>>(optionalUserRole,HttpStatus.FOUND);
          }    

        @PutMapping  
        public ResponseEntity<UserRole> updateUserRole(@RequestBody UserRole userRole) {
            logger.info("Updating user role with ID: {}", userRole.getId());
            UserRole updatedUserRole = userRoleService.updateUserRole(userRole);
            logger.info("Updated user role: {}", updatedUserRole);
            return new ResponseEntity<>(updatedUserRole, HttpStatus.OK);
          }

      @DeleteMapping("/{id}")
      public ResponseEntity<String> deleteUserRole(@PathVariable("id")long id){
         logger.info("Deleting user role with ID: {}", id);
         userRoleService.deleteUserRole(id);
         logger.info("Deleted user role with ID: {}", id);
         return new ResponseEntity<>("User Role Deleted Succesfully",HttpStatus.OK);
       }



}
