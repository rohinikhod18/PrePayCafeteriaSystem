package com.kpit.cps.service;

import com.kpit.cps.dto.ServingCounterDTO;
import com.kpit.cps.model.ServingCounter;
import java.util.*;


public interface ServingCounterService {

   public ServingCounter saveServingCounter(ServingCounterDTO servingCounterdCounterDTO);

   public  ServingCounter updateServingCounter(ServingCounter servingCounter);

   public List<ServingCounter> getAllServingCounters();

   public  Optional<ServingCounter> getServingCounterById(long id);

   public void  deleteServingCounter(long id);

}
