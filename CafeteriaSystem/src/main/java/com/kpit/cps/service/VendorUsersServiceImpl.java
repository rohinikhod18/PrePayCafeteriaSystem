package com.kpit.cps.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpit.cps.exception.AssociationNotFoundException;
import com.kpit.cps.exception.DuplicateDataException;
import com.kpit.cps.exception.IdNotFoundException;
import com.kpit.cps.model.Users;
import com.kpit.cps.model.Vendor;
import com.kpit.cps.model.VendorUsers;
import com.kpit.cps.repository.UsersRepository;
import com.kpit.cps.repository.VendorRepository;



@Service
public class VendorUsersServiceImpl implements VendorUsersService{
     
    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private UsersRepository userRepository;

    Logger logger=LoggerFactory.getLogger(VendorUsersServiceImpl.class);

    @Override
    public VendorUsers saveVendorUsers(VendorUsers vendorUsers) {
        Optional<Vendor> optionalVendor = vendorRepository.findById(vendorUsers.getVendorId());
        if (!optionalVendor.isPresent()) {
            logger.warn("Vendor with Id {} not found", vendorUsers.getVendorId());
            throw new IdNotFoundException("Vendor with Id not found");
        }
    
        Optional<Users> optionalUser = userRepository.findById(vendorUsers.getUserId());
        if (!optionalUser.isPresent()) {
            logger.warn("User with Id {} not found", vendorUsers.getUserId());
            throw new IdNotFoundException("User with Id not found");
        }

        Users user = optionalUser.get();
        Vendor vendor = optionalVendor.get();

        if (user.getVendorsList().contains(vendor)) {
            logger.info("Vendor ID {} is already associated with User ID {}", vendor.getId(), user.getId());
            throw new DuplicateDataException("This Vendor is already associated with the User.");
        }
        
        user.getVendorsList().add(vendor);
        vendor.getUsersList().add(user);
        
        vendorRepository.save(vendor);
        userRepository.save(user);
    
       
        return vendorUsers;
    }



    @Override
    public void removeVendorUsers(VendorUsers vendorUsers) {
        Optional<Vendor> optionalVendor = vendorRepository.findById(vendorUsers.getVendorId());
        if (!optionalVendor.isPresent()) {
            logger.warn("Vendor with Id {} not found", vendorUsers.getVendorId());
            throw new IdNotFoundException("Vendor with Id not found");
        }

        Optional<Users> optionalUser = userRepository.findById(vendorUsers.getUserId());
        if (!optionalUser.isPresent()) {
            logger.warn("User with Id {} not found", vendorUsers.getUserId());
            throw new IdNotFoundException("User with Id not found");
        }

        Users user = optionalUser.get();
        Vendor vendor = optionalVendor.get();

        if (!user.getVendorsList().contains(vendor)) {
            logger.info("Vendor ID {} is not associated with User ID {}", vendor.getId(), user.getId());
            throw new AssociationNotFoundException("This Vendor is not associated with the User.");
        }

        user.getVendorsList().remove(vendor);
        vendor.getUsersList().remove(user);

        vendorRepository.save(vendor);
        userRepository.save(user);

        logger.info("Successfully removed association between Vendor ID {} and User ID {}", vendor.getId(), user.getId());
    }

    
   
   

}
