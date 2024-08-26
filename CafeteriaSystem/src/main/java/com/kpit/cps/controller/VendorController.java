package com.kpit.cps.controller;

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
import java.util.*;
import com.kpit.cps.model.Vendor;
import com.kpit.cps.service.VendorService;

@RestController
@RequestMapping("/cafeteriaapi/vendor")
public class VendorController {

    @Autowired
    VendorService vendorService;

    Logger logger= LoggerFactory.getLogger(VendorController.class);

       @PostMapping
       ResponseEntity<Vendor>createVendor(@RequestBody Vendor vendor){
             logger.info("Creating new user with data: {}", vendor);
             Vendor createdVendor =vendorService.saveVendor(vendor);
             logger.info("Created user role with ID: {}", vendor.getId());
             return new ResponseEntity<Vendor>(createdVendor,HttpStatus.CREATED);
       }

      @GetMapping
      public ResponseEntity<List<Vendor>> getAllVendors() {
             logger.info("Fetching all vendors");
             List<Vendor> listOfAllVendors = vendorService.getAllVendors();
             logger.info("Fetched {} vendors", listOfAllVendors.size());
             return new ResponseEntity<>(listOfAllVendors, HttpStatus.OK);
       }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Vendor>> getVendorById(@PathVariable("id") long id) {
           logger.info("Fetching vendor with ID: {}", id);
            Optional<Vendor> optionalVendor = vendorService.getVendorById(id);
            logger.info("Fetched vendor: {}", optionalVendor.get());
            return new ResponseEntity<>(optionalVendor, HttpStatus.OK);   
    }

       @PutMapping
       ResponseEntity<Vendor> updateVendor(@RequestBody Vendor vendor){
             logger.info("Updating Vendor with ID: {}", vendor.getId());
             Vendor updatedVendor = vendorService.updateVendor(vendor);
             logger.info("Updated Vendor: {}", updatedVendor);
             return new ResponseEntity<Vendor>(updatedVendor, HttpStatus.OK); 
       }

       @DeleteMapping("/{id}")
       ResponseEntity<String> deleteVendor(@PathVariable("id")long id){
        logger.info("Deleting Vendor with ID: {}", id);
        vendorService.deleteVendor(id);
        logger.info("Deleted Vendor with ID: {}", id);
        return new ResponseEntity<>("Vendor Deleted Successfully",HttpStatus.OK);

       }

}
