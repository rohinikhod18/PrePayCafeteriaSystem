package com.kpit.cps.service;

import java.util.List;
import java.util.Optional;
import com.kpit.cps.model.Users;

public interface UsersService {

   public Users saveUser(Users user);

   public Users updateUser(Users user);

   public Optional<Users> getUserById(long id);

   public List<Users> getAllUsers();

   public void deleteUser(long id);

}
