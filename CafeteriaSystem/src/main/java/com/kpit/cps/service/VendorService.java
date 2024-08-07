package com.kpit.cps.service;

import com.kpit.cps.model.Vendor;
import java.util.*;

public interface VendorService {

   public Vendor saveVendor(Vendor vendor);

   public  Vendor updateVendor(Vendor vendor);

   public List<Vendor> getAllVendors();

   public  Optional<Vendor> getVendorById(long id);

   public void  deleteVendor(long id);
}
