package com.kpit.cps.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpit.cps.exception.DataIsNotPresentException;
import com.kpit.cps.exception.DuplicateDataException;
import com.kpit.cps.exception.IdNotFoundException;
import com.kpit.cps.model.Vendor;
import com.kpit.cps.repository.VendorRepository;

@Service
public class VendorServiceImpl implements VendorService {

    Logger logger= LoggerFactory.getLogger(VendorServiceImpl.class);

    @Autowired
    VendorRepository vendorRepository;

    @Override
    public Vendor saveVendor(Vendor vendor) {
        logger.info("Saving vendor: {}", vendor);
        Optional<Vendor> optionalVendor =vendorRepository.findByVendorName(vendor.getVendorName()); 
        if(optionalVendor.isPresent()){
            logger.warn("Duplicate Vendor name detected: {}", vendor.getVendorName());
            throw new DuplicateDataException("Vendor is already Present");
        }
        Vendor savedVendor = vendorRepository.save(vendor);
        logger.info("Saved vendor with ID: {}", vendor.getId());   
        return savedVendor;
    }

    @Override
    public Vendor updateVendor(Vendor vendor) {
         logger.info("Updating vendor with ID: {}", vendor.getId());
        Optional<Vendor>optionalVendor= vendorRepository.findById(vendor.getId());
         if (!optionalVendor.isPresent()) {
            logger.warn("Vendor not found with ID: {}", vendor.getId());
            throw new IdNotFoundException("UserRole not found with ID: " + vendor.getId());
         }
         Vendor updatedVendor = vendorRepository.save(vendor);
         logger.info("Updated vendor: {}", updatedVendor);
         return updatedVendor;
    }

    @Override
    public List<Vendor> getAllVendors() {
        logger.info("Fetching all Vendors");
        List<Vendor> vendors = vendorRepository.findAll();   
        if (vendors.isEmpty()) {
            logger.warn("No Vendors found");
            throw new DataIsNotPresentException("No Vendors found");
        }
       logger.info("Fetched {} Vendors", vendors.size()); 
       return vendors;
    }

    @Override
    public Optional<Vendor> getVendorById(long id) {
         logger.info("Fetching vendor with ID: {}", id);
        Optional<Vendor> optionalVendor = vendorRepository.findById(id);
        if (!optionalVendor.isPresent()) {
            logger.warn("Vendor not found with ID: {}", id);
            throw new IdNotFoundException("User not found with ID: " + id);
        }
        logger.info("Fetched vendor: {}");
        return optionalVendor;
    }

    @Override
    public void deleteVendor(long id) {
        logger.info("Deleting vendor with ID: {}", id);
        Optional<Vendor>optionalVendor= vendorRepository.findById(id);
         if (!optionalVendor.isPresent()) {
            logger.warn("Vendor not found with ID: {}", id);
            throw new IdNotFoundException("Vendor not found with ID: " + id);
         }
         logger.warn("Vendor not found with ID: {}", id);
         vendorRepository.deleteById(id);
    }

}
