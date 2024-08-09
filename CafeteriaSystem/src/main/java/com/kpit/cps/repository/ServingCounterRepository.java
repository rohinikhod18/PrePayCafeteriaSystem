package com.kpit.cps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import com.kpit.cps.model.ServingCounter;

@Repository
public interface ServingCounterRepository  extends JpaRepository<ServingCounter,Long>{
        Optional<ServingCounter> findByName(String name);
}
