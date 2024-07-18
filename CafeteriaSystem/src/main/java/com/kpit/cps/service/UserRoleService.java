package com.kpit.cps.service;

import java.util.List;
import java.util.Optional;

import com.kpit.cps.model.UserRole;

public interface UserRoleService {

       public UserRole saveUserRole(UserRole userrole);

       public UserRole updateUserRole(UserRole userrole);

       public Optional<UserRole> getUserRoleById(long id);

       public List<UserRole> getAllUserRoles();

       public void deleteUserRole(long id);

}
