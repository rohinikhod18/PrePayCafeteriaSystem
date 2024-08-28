package com.kpit.cps.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.kpit.cps.dto.ServingCounterDTO;
import com.kpit.cps.model.ServingCounter;
import com.kpit.cps.model.Vendor;
import com.kpit.cps.repository.ServingCounterRepository;
import com.kpit.cps.repository.VendorRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ServingCounterServiceImplTest {

    @Mock
    private ServingCounterRepository servingCounterRepository;

    @InjectMocks
    private ServingCounterServiceImpl servingCounterServiceImpl;

    @Mock
    private VendorRepository vendorRepository;

    @Mock
    private ModelMapper modelMapper;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private ServingCounter expectedServingCounter() {
        ServingCounter servingCounter = new ServingCounter();
        Vendor vendor = new Vendor();
        servingCounter.setId(1L);
        servingCounter.setName("Counter 1");
        servingCounter.setVendor(vendor);
        return servingCounter;
    }

    private ServingCounterDTO expectedServingCounterDTO() {
        ServingCounterDTO servingCounterDTO = new ServingCounterDTO();
        servingCounterDTO.setId(1L);
        servingCounterDTO.setName("Counter 1");
        servingCounterDTO.setVendorId(1L);
        return servingCounterDTO;
    }

    @Test
    public void testSaveServingCounter() {
        ServingCounterDTO servingCounterDTO = expectedServingCounterDTO();
        ServingCounter servingCounter = expectedServingCounter();
        when(modelMapper.map(servingCounterDTO, ServingCounter.class)).thenReturn(servingCounter);
        when(vendorRepository.findById(servingCounterDTO.getVendorId())).thenReturn(Optional.of(servingCounter.getVendor()));
        when(servingCounterRepository.findByName(servingCounter.getName())).thenReturn(Optional.empty());
        when(servingCounterRepository.save(servingCounter)).thenReturn(servingCounter);
        ServingCounter savedServingCounter = servingCounterServiceImpl.saveServingCounter(servingCounterDTO);
        assertEquals(servingCounter.getId(), savedServingCounter.getId());
        assertEquals(servingCounter.getName(), savedServingCounter.getName());
    }

    @Test
    public void testUpdateServingCounter() {
        ServingCounter servingCounter = expectedServingCounter();
        when(servingCounterRepository.findById(servingCounter.getId())).thenReturn(Optional.of(servingCounter));
        when(servingCounterRepository.save(servingCounter)).thenReturn(servingCounter);
        ServingCounter updatedServingCounter = servingCounterServiceImpl.updateServingCounter(servingCounter);
        assertEquals(servingCounter.getId(), updatedServingCounter.getId());
        assertEquals(servingCounter.getName(), updatedServingCounter.getName());
    }

    @Test
    public void testGetAllServingCounters() {
        List<ServingCounter> servingCountersList = List.of(expectedServingCounter());
        when(servingCounterRepository.findAll()).thenReturn(servingCountersList);
        List<ServingCounter> foundServingCountersList = servingCounterServiceImpl.getAllServingCounters();
        assertEquals(servingCountersList.size(), foundServingCountersList.size());
        assertEquals(servingCountersList.get(0).getId(), foundServingCountersList.get(0).getId());
    }

    @Test
    public void testGetServingCounterById() {
        ServingCounter servingCounter = expectedServingCounter();
        when(servingCounterRepository.findById(servingCounter.getId())).thenReturn(Optional.of(servingCounter));
        Optional<ServingCounter> foundServingCounter = servingCounterServiceImpl.getServingCounterById(servingCounter.getId());
        assertEquals(servingCounter.getId(), foundServingCounter.get().getId());
        assertEquals(servingCounter.getName(), foundServingCounter.get().getName());
    }

    @Test
    public void testDeleteServingCounter() {
        long id = 1L;
        ServingCounter servingCounter = expectedServingCounter();
        when(servingCounterRepository.findById(id)).thenReturn(Optional.of(servingCounter));
        servingCounterServiceImpl.deleteServingCounter(id);
        verify(servingCounterRepository).deleteById(id);
    }


}
