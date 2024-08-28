package com.kpit.cps.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kpit.cps.dto.ServingCounterDTO;
import com.kpit.cps.model.ServingCounter;

import com.kpit.cps.service.ServingCounterService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ServingCounterControllerTest {

    @Mock
    ServingCounterService servingCounterService;

    @InjectMocks
    ServingCounterController servingCounterController;

     public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private ServingCounter expectedServingCounter() {
        ServingCounter servingCounter = new ServingCounter();
        servingCounter.setId(1L);
        servingCounter.setName("Counter 1");
        servingCounter.setActive(true);
        servingCounter.setUpdatedBy(1L);
        servingCounter.setUpdatedOn("2024-08-27 15:00");
        servingCounter.setCreatedBy(1L);
        servingCounter.setCreatedOn("2024-08-27 14:30");
        return servingCounter;
    }

    private ServingCounterDTO expectedServingCounterDTO() {
        ServingCounterDTO servingCounterDTO = new ServingCounterDTO();
        servingCounterDTO.setId(1L);
        servingCounterDTO.setName("Counter 1");
        servingCounterDTO.setActive(true);
        servingCounterDTO.setUpdatedBy(1L);
        servingCounterDTO.setUpdatedOn("2024-08-27 15:00");
        servingCounterDTO.setCreatedBy(1L);
        servingCounterDTO.setCreatedOn("2024-08-27 14:30");
        servingCounterDTO.setVendorId(1L); 
        return servingCounterDTO;
    }

    @Test
    public void testCreateServingCounter() {
        ServingCounterDTO servingCounterDTO = expectedServingCounterDTO();
        ServingCounter servingCounter = expectedServingCounter();
        when(servingCounterService.saveServingCounter(servingCounterDTO)).thenReturn(servingCounter);
        ResponseEntity<ServingCounter> response = servingCounterController.createServingCounter(servingCounterDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(servingCounter, response.getBody());
        verify(servingCounterService, times(1)).saveServingCounter(any(ServingCounterDTO.class));
    }

    @Test
    public void testGetAllServingCounters() {
        List<ServingCounter> servingCountersList = List.of(expectedServingCounter());
        when(servingCounterService.getAllServingCounters()).thenReturn(servingCountersList);
        ResponseEntity<List<ServingCounter>> response = servingCounterController.getAllServingCounters();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(servingCountersList, response.getBody());
        verify(servingCounterService, times(1)).getAllServingCounters();
    }

    @Test
    public void testGetServingCounterById() {
        long id = 1L;
        ServingCounter servingCounter = expectedServingCounter();
        Optional<ServingCounter> optionalServingCounter = Optional.of(servingCounter);
        when(servingCounterService.getServingCounterById(id)).thenReturn(optionalServingCounter);
        ResponseEntity<Optional<ServingCounter>> response = servingCounterController.getServingCounterById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(optionalServingCounter, response.getBody());
        verify(servingCounterService, times(1)).getServingCounterById(id);
    }

    @Test
    public void testUpdateServingCounter() {
        ServingCounter servingCounter = expectedServingCounter();
        when(servingCounterService.updateServingCounter(any(ServingCounter.class))).thenReturn(servingCounter);
        ResponseEntity<ServingCounter> response = servingCounterController.updateServingCounter(servingCounter);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(servingCounter, response.getBody());
        verify(servingCounterService, times(1)).updateServingCounter(any(ServingCounter.class));
    }

    @Test
    public void testDeleteServingCounter() {
        long id = 1L;
        doNothing().when(servingCounterService).deleteServingCounter(id);

        ResponseEntity<String> response = servingCounterController.deleteServingCounter(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Serving Counter Deleted Successfully", response.getBody());
        verify(servingCounterService, times(1)).deleteServingCounter(id);
    }

}
