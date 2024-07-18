package com.kpit.cps.service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public UserRole saveUserRole(UserRole userrole) {
        Optional<UserRole> optionalUserRole =userRoleRepository.findByRoleName(userrole.getRoleName()); 
        if(optionalUserRole.isPresent()){
              throw new DuplicateDataException("UserRole is already Present");
        }
         return  userRoleRepository.save(userrole);
    }

  
    @Override
    public UserRole updateUserRole(UserRole userrole) {
        Optional<UserRole>optionalUserRole= userRoleRepository.findById(userrole.getId());
         if (!optionalUserRole.isPresent()) {
                throw new IdNotFoundException("UserRole not found with ID: " + userrole.getId());
         }
        return userRoleRepository.save(userrole);
    }

    @Override
    public Optional<UserRole> getUserRoleById(long id) {
        Optional<UserRole>optionalUserRole= userRoleRepository.findById(id);
         if (!optionalUserRole.isPresent()) {
                throw new IdNotFoundException("UserRole not found with ID: " + id);
         }
         return optionalUserRole;
    }

    @Override
    public List<UserRole> getAllUserRoles() {
        List<UserRole> userRoles = userRoleRepository.findAll();   
        if (userRoles.isEmpty()) {
            throw new DataIsNotPresentException("No Roles found");
        }
       return userRoles;
    }

    @Override
    public void deleteUserRole(long id) {
        Optional<UserRole>optionalUserRole= userRoleRepository.findById(id);
         if (!optionalUserRole.isPresent()) {
                throw new IdNotFoundException("UserRole not found with ID: " + id);
         }
         userRoleRepository.deleteById(id);
    }

  

}
