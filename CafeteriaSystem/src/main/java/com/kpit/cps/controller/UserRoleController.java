package com.kpit.cps.controller;

import java.util.List;
import java.util.Optional;

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
@RequestMapping("/cafeteriaapi/cafeteria")
public class UserRoleController {

      @Autowired
      UserRoleService userRoleService;

      @PostMapping
      public ResponseEntity<UserRole> createUserRole(@RequestBody UserRole userRole){
         UserRole createdUserRole=userRoleService.saveUserRole(userRole);
         return new ResponseEntity<UserRole>(createdUserRole,HttpStatus.CREATED);
        }
      
      @GetMapping
      public ResponseEntity<List<UserRole>> getAllUserRoles(){
         List<UserRole> listOfAllUserRoles=userRoleService.getAllUserRoles();
         return new ResponseEntity<List<UserRole>>(listOfAllUserRoles,HttpStatus.FOUND);
        }
      
      @GetMapping("/{id}")
      public ResponseEntity<Optional<UserRole>> getUserRoleById(@PathVariable("id")long id){
            Optional<UserRole>optionalUserRole=userRoleService.getUserRoleById(id);
            return new ResponseEntity<Optional<UserRole>>(optionalUserRole,HttpStatus.FOUND);
          }    

      @PutMapping  
      public ResponseEntity<UserRole> updateUserRole(@RequestBody UserRole userRole){
         UserRole updatedUserRole=userRoleService.saveUserRole(userRole);
         return new ResponseEntity<UserRole>(updatedUserRole,HttpStatus.OK);
       }

      @DeleteMapping("/{id}")
      public ResponseEntity<String> deleteUserRole(@PathVariable("id")long id){
         userRoleService.deleteUserRole(id);
         return new ResponseEntity<>("User Role Deleted Succefully",HttpStatus.OK);
       }



}
