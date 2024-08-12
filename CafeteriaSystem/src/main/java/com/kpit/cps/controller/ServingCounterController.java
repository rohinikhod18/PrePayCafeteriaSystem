package com.kpit.cps.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.kpit.cps.dto.ServingCounterDTO;
import com.kpit.cps.model.ServingCounter;
import com.kpit.cps.service.ServingCounterService;

@RestController
@RequestMapping("/cafeteriaapi/servingCounter")
public class ServingCounterController {

    @Autowired
    private ServingCounterService servingCounterService;

    private Logger logger = LoggerFactory.getLogger(ServingCounterController.class);

    @PostMapping
    public ResponseEntity<ServingCounter> createServingCounter(@RequestBody ServingCounterDTO servingCounterDTO) {
        logger.info("Creating new serving counter with data: {}", servingCounterDTO);
        ServingCounter createdServingCounter = servingCounterService.saveServingCounter(servingCounterDTO);
        logger.info("Created serving counter with ID: {}", createdServingCounter.getId());
        return new ResponseEntity<>(createdServingCounter, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ServingCounter>> getServingCounterById(@PathVariable("id") long id) {
        logger.info("Fetching serving counter with ID: {}", id);
        Optional<ServingCounter> optionalServingCounter = servingCounterService.getServingCounterById(id);
        logger.info("Fetched serving counter: {}", optionalServingCounter.get());
        return new ResponseEntity<>(optionalServingCounter, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ServingCounter>> getAllServingCounters() {
        logger.info("Fetching all serving counters");
        List<ServingCounter> servingCounters = servingCounterService.getAllServingCounters();
        return new ResponseEntity<>(servingCounters, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ServingCounter> updateServingCounter(@RequestBody ServingCounter servingCounter) {
        logger.info("Updating serving counter with ID: {}", servingCounter.getId());
        ServingCounter updatedServingCounter = servingCounterService.updateServingCounter(servingCounter);
        logger.info("Updated serving counter: {}", updatedServingCounter);
        return new ResponseEntity<>(updatedServingCounter, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteServingCounter(@PathVariable("id") long id) {
        logger.info("Deleting serving counter with ID: {}", id);
        servingCounterService.deleteServingCounter(id);
        logger.info("Deleted serving counter with ID: {}", id);
        return new ResponseEntity<>("Serving Counter Deleted Successfully", HttpStatus.OK);
    }

}
