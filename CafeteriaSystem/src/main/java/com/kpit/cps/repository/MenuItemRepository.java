package com.kpit.cps.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kpit.cps.model.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem,Long>{
      Optional<MenuItem> findByName(String name);  
}
