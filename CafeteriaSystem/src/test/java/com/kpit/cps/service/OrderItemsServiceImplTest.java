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

import com.kpit.cps.dto.OrderItemsDTO;
import com.kpit.cps.model.MenuItem;
import com.kpit.cps.model.Order;
import com.kpit.cps.model.OrderItems;
import com.kpit.cps.repository.MenuItemRepository;
import com.kpit.cps.repository.OrderItemsRepository;
import com.kpit.cps.repository.OrderRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class OrderItemsServiceImplTest {

    @Mock
    private OrderItemsRepository orderItemsRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private OrderItemsServiceImpl orderItemsServiceImpl;

     @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    private OrderItems expectedOrderItems() {
        OrderItems orderItems = new OrderItems();
        Order order = new Order(); 
        MenuItem menuItem = new MenuItem(); 
        orderItems.setId(1L);
        orderItems.setOrder(order);  
        orderItems.setMenuItem(menuItem);  
        orderItems.setUnitPrice(100.0);
        orderItems.setTotalPrice(200.0);
        orderItems.setQuantity(2);
        orderItems.setStatus("CREATED");
        orderItems.setCreatedBy(1L);
        orderItems.setCreatedOn("2024-08-27");
        orderItems.setUpdatedBy(2L);
        orderItems.setUpdatedOn("2024-08-28");
        return orderItems;
    }

    private OrderItemsDTO expectedOrderItemsDTO() {
        OrderItemsDTO orderItemsDTO = new OrderItemsDTO();
        orderItemsDTO.setId(1L);
        orderItemsDTO.setOrderId(1L);
        orderItemsDTO.setMenuItemId(1L);
        orderItemsDTO.setUnitPrice(100.0);
        orderItemsDTO.setTotalPrice(200.0);
        orderItemsDTO.setQuantity(2);
        orderItemsDTO.setStatus("CREATED");
        orderItemsDTO.setCreatedBy(1L);
        orderItemsDTO.setCreatedOn("2024-08-27");
        orderItemsDTO.setUpdatedBy(2L);
        orderItemsDTO.setUpdatedOn("2024-08-28");
        return orderItemsDTO;
    }

    @Test
    public void testSaveOrderItems() {
    
        OrderItemsDTO orderItemsDTO = expectedOrderItemsDTO();
        OrderItems orderItems = expectedOrderItems();
        when(modelMapper.map(orderItemsDTO, OrderItems.class)).thenReturn(orderItems);
        when(orderRepository.findById(orderItemsDTO.getOrderId())).thenReturn(Optional.of(orderItems.getOrder()));
        when(menuItemRepository.findById(orderItemsDTO.getMenuItemId())).thenReturn(Optional.of(orderItems.getMenuItem()));
        when(orderItemsRepository.save(orderItems)).thenReturn(orderItems);

       OrderItems savedOrderItems = orderItemsServiceImpl.saveOrderItems(orderItemsDTO);
        assertEquals(orderItems.getOrder(), savedOrderItems.getOrder());
        assertEquals(orderItems.getMenuItem(), savedOrderItems.getMenuItem());  
        assertEquals(orderItems, savedOrderItems);
    }

    @Test
    public void testUpdateOrderItems() {
        OrderItems orderItems = expectedOrderItems();
        when(orderItemsRepository.findById(orderItems.getId())).thenReturn(Optional.of(orderItems));
        when(orderItemsRepository.save(orderItems)).thenReturn(orderItems);
        OrderItems updatedOrderItems = orderItemsServiceImpl.updateOrderItems(orderItems);
        assertEquals(orderItems, updatedOrderItems);
    }


    @Test
    public void testGetAllOrderItems() {
        List<OrderItems> orderItemsList = List.of(expectedOrderItems());
        when(orderItemsRepository.findAll()).thenReturn(orderItemsList);
        List<OrderItems> foundOrderItemsList = orderItemsServiceImpl.getAllOrderItems();
        assertEquals(orderItemsList.size(), foundOrderItemsList.size());
        assertEquals(orderItemsList.get(0).getId(), foundOrderItemsList.get(0).getId());
    }

    @Test
    public void testGetOrderItemsById() {
        OrderItems orderItems = expectedOrderItems();
        when(orderItemsRepository.findById(orderItems.getId())).thenReturn(Optional.of(orderItems));

        Optional<OrderItems> foundOrderItems = orderItemsServiceImpl.getOrderItemsById(orderItems.getId());
        assertEquals(orderItems.getId(), foundOrderItems.get().getId());
        assertEquals(orderItems.getOrder(), foundOrderItems.get().getOrder());
        assertEquals(orderItems.getMenuItem(), foundOrderItems.get().getMenuItem());
        assertEquals(orderItems, foundOrderItems.get());
    }

    @Test
    public void testDeleteOrderItems() {
        long id = 1L;
        OrderItems orderItems = expectedOrderItems();
        when(orderItemsRepository.findById(id)).thenReturn(Optional.of(orderItems));
        orderItemsServiceImpl.deleteOrderItems(id);
        verify(orderItemsRepository).deleteById(id);
    }

     
}
