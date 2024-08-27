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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kpit.cps.dto.OrderItemsDTO;
import com.kpit.cps.model.MenuItem;
import com.kpit.cps.model.Order;
import com.kpit.cps.model.OrderItems;
import com.kpit.cps.service.OrderItemsService;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class OrderItemsControllerTest {

    @Mock
    OrderItemsService orderItemsService;

    @InjectMocks
    OrderItemsController orderItemsController;


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
    public void testCreateOrderItems() {
        OrderItemsDTO orderItemsDTO = expectedOrderItemsDTO();
        OrderItems orderItems = expectedOrderItems();
        when(orderItemsService.saveOrderItems(any(OrderItemsDTO.class))).thenReturn(orderItems);

        ResponseEntity<OrderItems> response = orderItemsController.createOrderItem(orderItemsDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(orderItems, response.getBody());
        verify(orderItemsService, times(1)).saveOrderItems(any(OrderItemsDTO.class));
    }

    @Test
    public void testGetAllOrderItems() {
        List<OrderItems> orderItemsList = List.of(expectedOrderItems());
        when(orderItemsService.getAllOrderItems()).thenReturn(orderItemsList);

        ResponseEntity<List<OrderItems>> response = orderItemsController.getAllOrderItems();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orderItemsList, response.getBody());
        verify(orderItemsService, times(1)).getAllOrderItems();
    }

    @Test
    public void testGetOrderItemsById() {
        long id = 1L;
        OrderItems orderItems = expectedOrderItems();
        Optional<OrderItems> optionalOrderItems = Optional.of(orderItems);

        when(orderItemsService.getOrderItemsById(id)).thenReturn(optionalOrderItems);
        ResponseEntity<Optional<OrderItems>> response = orderItemsController.getOrderItemById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(optionalOrderItems, response.getBody());
        verify(orderItemsService, times(1)).getOrderItemsById(id);
    }

    @Test
    public void testUpdateOrderItems() {
        OrderItems orderItems = expectedOrderItems();
        when(orderItemsService.updateOrderItems(any(OrderItems.class))).thenReturn(orderItems);

        ResponseEntity<OrderItems> response = orderItemsController.updateOrderItem(orderItems);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(orderItems, response.getBody());
        verify(orderItemsService, times(1)).updateOrderItems(any(OrderItems.class));
    }

    @Test
    public void testDeleteOrderItems() {
        long id = 1L;
        doNothing().when(orderItemsService).deleteOrderItems(id);

        ResponseEntity<String> response = orderItemsController.deleteOrderItem(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Order Item Deleted Successfully", response.getBody());
        verify(orderItemsService, times(1)).deleteOrderItems(id);
    }


}
