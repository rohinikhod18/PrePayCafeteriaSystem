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
import com.kpit.cps.model.UserRole;
import com.kpit.cps.repository.UserRoleRepository;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    UserRoleRepository userRoleRepository;

    Logger logger=LoggerFactory.getLogger(UserRoleServiceImpl.class);

    @Override
    public UserRole saveUserRole(UserRole userrole) {
        logger.info("Saving user role: {}", userrole);
        Optional<UserRole> optionalUserRole =userRoleRepository.findByRoleName(userrole.getRoleName()); 
        if(optionalUserRole.isPresent()){
            logger.warn("Duplicate user role detected: {}", userrole.getRoleName());
            throw new DuplicateDataException("UserRole is already Present");
        }
        UserRole savedUserRole = userRoleRepository.save(userrole);
        logger.info("Saved user role with ID: {}", savedUserRole.getId());   
        return savedUserRole;
    }
    

  
    public UserRole updateUserRole(UserRole userrole) {
        logger.info("Updating user role with ID: {}", userrole.getId());
        Optional<UserRole> optionalUserRole = userRoleRepository.findById(userrole.getId());
        if (!optionalUserRole.isPresent()) {
            logger.warn("User role not found with ID: {}", userrole.getId());
            throw new IdNotFoundException("UserRole not found with ID: " + userrole.getId());
        }
        UserRole updatedUserRole = userRoleRepository.save(userrole);
        logger.info("Updated user role: {}", updatedUserRole);
        return updatedUserRole;
    }

    @Override
    public Optional<UserRole> getUserRoleById(long id) {
        logger.info("Fetching user role with ID: {}", id);
        Optional<UserRole>optionalUserRole= userRoleRepository.findById(id);
         if (!optionalUserRole.isPresent()) {
            logger.warn("User role not found with ID: {}", id);
            throw new IdNotFoundException("UserRole not found with ID: " + id);
         }
         logger.info("Fetched user role {}");
         return optionalUserRole;
    }

    @Override
    public List<UserRole> getAllUserRoles() {
        logger.info("Fetching all user roles");
        List<UserRole> userRoles = userRoleRepository.findAll();   
        if (userRoles.isEmpty()) {
            logger.warn("No user roles found");
            throw new DataIsNotPresentException("No Roles found");
        }
       logger.info("Fetched {} user roles", userRoles.size()); 
       return userRoles;
    }

    @Override
    public void deleteUserRole(long id) {
        logger.info("Deleting user role with ID: {}", id);
        Optional<UserRole>optionalUserRole= userRoleRepository.findById(id);
         if (!optionalUserRole.isPresent()) {
            logger.warn("User role not found with ID: {}", id);
            throw new IdNotFoundException("UserRole not found with ID: " + id);
         }
         logger.info("UserRole deleted with ID{}", id); 
         userRoleRepository.deleteById(id);
    }

  

}
