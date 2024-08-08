package com.kpit.cps.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kpit.cps.model.VendorUsers;
import com.kpit.cps.service.VendorUsersService;



@RequestMapping("/cafeteriaapi/vendorusers")
@RestController
public class VendorUsersController {

    Logger logger=LoggerFactory.getLogger(VendorUsersController.class);

    @Autowired
    VendorUsersService vendorUsersService;

    @PostMapping
    ResponseEntity<VendorUsers> createVendorUsers(@RequestBody VendorUsers vendorUsers){
        logger.info("Received request to create vendor-user association: {}", vendorUsers);
        VendorUsers savedVendorUsers = vendorUsersService.saveVendorUsers(vendorUsers);
        logger.info("Successfully created vendor-user association: {}", savedVendorUsers);
        return new ResponseEntity<>(savedVendorUsers,HttpStatus.CREATED);
    }

      @DeleteMapping
       ResponseEntity<String> removeVendorUsers(@RequestBody VendorUsers vendorUsers){
        logger.info("Received request to remove vendor-user association: {}", vendorUsers);
        vendorUsersService.removeVendorUsers(vendorUsers);
        logger.info("Successfully removed vendor-user association: {}", vendorUsers);
        return new ResponseEntity<>("VendorUsers Deleted Succefully",HttpStatus.OK);
       }

    
}
