package com.kpit.cps.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kpit.cps.model.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Long>{
    
         Optional<UserRole> findByRoleName(String roleName);
}
