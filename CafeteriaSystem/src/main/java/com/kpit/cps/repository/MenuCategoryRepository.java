package com.kpit.cps.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kpit.cps.model.MenuCategory;


@Repository
public interface MenuCategoryRepository extends JpaRepository<MenuCategory,Long> {
         Optional<MenuCategory> findByName(String name);      
}
