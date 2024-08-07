package com.kpit.cps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kpit.cps.model.Vendor;
import java.util.Optional;

@Repository
public interface VendorRepository  extends JpaRepository<Vendor,Long>{
           Optional<Vendor> findByVendorName(String userName);
}

