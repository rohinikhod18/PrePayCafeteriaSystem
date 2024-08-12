package com.kpit.cps.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpit.cps.dto.UsersRequestDTO;
import com.kpit.cps.exception.DataIsNotPresentException;
import com.kpit.cps.exception.DuplicateDataException;
import com.kpit.cps.exception.IdNotFoundException;
import com.kpit.cps.model.UserRole;
import com.kpit.cps.model.Users;
import com.kpit.cps.repository.UserRoleRepository;
import com.kpit.cps.repository.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService{

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private ModelMapper modelMapper;

    Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);

 @Override
 public Users saveUser(UsersRequestDTO userrRequestDTO) {

    logger.info("Saving user: {}", userrRequestDTO);
    Users user = modelMapper.map(userrRequestDTO, Users.class);
    
    Optional< UserRole> userRole = userRoleRepository.findById(userrRequestDTO.getUserRoleId());
    if(!userRole.isPresent()){
       throw new IdNotFoundException("UserRole not found with ID: " + userrRequestDTO.getUserRoleId());
     }
    user.setUserRole(userRole.get()); 
    Optional<Users> optionalUser =usersRepository.findByUserName(user.getUserName()); 
    if(optionalUser.isPresent()){
        logger.warn("Duplicate user  detected: {}", user.getUserName());
        throw new DuplicateDataException("User is already Present");
    }
    Users savedUser = usersRepository.save(user);
    logger.info("Saved user with ID: {}", savedUser.getId());
    return savedUser;
}

    @Override
    public Users updateUser(Users user) {
        logger.info("Updating user with ID: {}", user.getId());
        Optional<Users> optionalUser = usersRepository.findById(user.getId());
        if (!optionalUser.isPresent()) {
            logger.warn("User not found with ID: {}", user.getId());
            throw new IdNotFoundException("User not found with ID: " + user.getId());
        }
        Users updatedUser = usersRepository.save(user);
        logger.info("Updated user: {}", updatedUser);
        return updatedUser;
    }

    @Override
    public Optional<Users> getUserById(long id) {
        logger.info("Fetching user with ID: {}", id);
        Optional<Users> optionalUser = usersRepository.findById(id);
        if (!optionalUser.isPresent()) {
            logger.warn("User not found with ID: {}", id);
            throw new IdNotFoundException("User not found with ID: " + id);
        }
        logger.info("Fetched user: {}", optionalUser.get());
        return optionalUser;
    }

    @Override
    public List<Users> getAllUsers() {
      logger.info("Fetching all users");
        List<Users> users = usersRepository.findAll();
        if (users.isEmpty()) {
            logger.warn("No users found");
            throw new DataIsNotPresentException("No users found");
        }
        logger.info("Fetched {} users", users.size());
        return users;
    }

    @Override
    public void deleteUser(long id) {
        logger.info("Deleting user with ID: {}", id);
        Optional<Users> optionalUser = usersRepository.findById(id);
        if (!optionalUser.isPresent()) {
            logger.warn("User not found with ID: {}", id);
            throw new IdNotFoundException("User not found with ID: " + id);
        }
        usersRepository.deleteById(id);
        logger.info("Deleted user with ID: {}", id);
    }

       

}
