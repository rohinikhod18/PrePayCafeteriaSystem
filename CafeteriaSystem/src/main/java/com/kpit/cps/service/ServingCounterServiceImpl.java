package com.kpit.cps.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpit.cps.exception.DataIsNotPresentException;
import com.kpit.cps.exception.DuplicateDataException;
import com.kpit.cps.exception.IdNotFoundException;
import com.kpit.cps.model.ServingCounter;
import com.kpit.cps.repository.ServingCounterRepository;

@Service
public class ServingCounterServiceImpl implements ServingCounterService {

     Logger logger = LoggerFactory.getLogger(ServingCounterService.class);

     @Autowired
     ServingCounterRepository servingCounterRepository;

    @Override
    public ServingCounter saveServingCounter(ServingCounter servingCounter) {
        logger.info("Saving ServingCounter: {}", servingCounter);
        Optional<ServingCounter> optionalServingCounter =servingCounterRepository.findByName(servingCounter.getName()); 
        if(optionalServingCounter.isPresent()){
            logger.warn("Duplicate ServingCounter name detected: {}", servingCounter.getName());
            throw new DuplicateDataException("ServingCounter is already Present");
        }
        ServingCounter savedServiceCounter = servingCounterRepository.save(servingCounter);
        logger.info("Saved ServiceCounter with ID: {}", servingCounter.getId());   
        return savedServiceCounter;
    }
    

    @Override
    public ServingCounter updateServingCounter(ServingCounter servingCounter) {
       logger.info("Updating ServingCounter with ID: {}", servingCounter.getId());
        Optional<ServingCounter>optionalServiceCounter= servingCounterRepository.findById(servingCounter.getId());
         if (!optionalServiceCounter.isPresent()) {
            logger.warn("ServingCounter not found with ID: {}", servingCounter.getId());
            throw new IdNotFoundException("ServingCounter not found with ID: " + servingCounter.getId());
         }
         ServingCounter updatedServingCounter = servingCounterRepository.save(servingCounter);
         logger.info("Updated ServingCounter: {}", updatedServingCounter);
         return updatedServingCounter;
    }

    @Override
    public List<ServingCounter> getAllServingCounters() {
         logger.info("Fetching all ServingCounter");
        List<ServingCounter> servingCounters = servingCounterRepository.findAll();   
        if (servingCounters.isEmpty()) {
            logger.warn("No ServiceCounter found");
            throw new DataIsNotPresentException("No ServiceCounter found");
        }
       logger.info("Fetched {} ServiceCounters", servingCounters.size()); 
       return servingCounters;
        
    }

    @Override
    public Optional<ServingCounter> getServingCounterById(long id) {
        logger.info("Fetching ServingCounter with ID: {}", id);
        Optional<ServingCounter> optionalServingCounter = servingCounterRepository.findById(id);
        if (!optionalServingCounter.isPresent()) {
            logger.warn("ServingCounter not found with ID: {}", id);
            throw new IdNotFoundException("ServingCounter not  found with ID: " + id);
        }
        logger.info("Fetched ServingCounter: {}");
        return optionalServingCounter;
      
    }

    @Override
    public void deleteServingCounter(long id) {
        logger.info("Deleting ServingCounter with ID: {}", id);
        Optional<ServingCounter>optionalServingCounter= servingCounterRepository.findById(id);
         if (!optionalServingCounter.isPresent()) {
            logger.warn("ServingCounter not found with ID: {}", id);
            throw new IdNotFoundException("ServingCounter not found with ID: " + id);
         }
         logger.warn("ServingCounter not found with ID: {}", id);
         servingCounterRepository.deleteById(id);
    }

   

     
        
    }

    

