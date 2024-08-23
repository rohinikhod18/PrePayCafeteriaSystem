package com.kpit.cps.controller;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.*;
import java.time.LocalDateTime;
import java.util.List;

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

import com.kpit.cps.model.Order;
import com.kpit.cps.service.OrderService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

     @Mock
       OrderService orderService;

    @InjectMocks
    OrderController orderController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private Order expectedOrder() {
        Order order = new Order();
         order.setId(1L);
        order.setOrderNumber("ORD123456");
        order.setTotalPrice(250.0);
        order.setStatus('1'); 
        order.setCreatedBy(1L);
        order.setCreatedOn(LocalDateTime.of(2024, 8, 23, 14, 30));
        order.setUpdatedBy(1L);
        order.setUpdatedOn(LocalDateTime.of(2024, 8, 23, 15, 00));
        return order;
    }

     @Test
    public void testCreateOrder() {
        Order order = expectedOrder();
        when(orderService.saveOrder(order)).thenReturn(order);

        ResponseEntity<Order> response = orderController.createOrder(order);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(order, response.getBody());
        verify(orderService, times(1)).saveOrder(order);
    }

    @Test
    public void testGetAllOrders() {
        List<Order> orders = List.of(expectedOrder());
        when(orderService.getAllOrders()).thenReturn(orders);

        ResponseEntity<List<Order>> response = orderController.getAllOrders();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orders, response.getBody());
        verify(orderService, times(1)).getAllOrders();
    }

    @Test
    public void testGetOrderById() {
        long id = 1L;
        Order order = expectedOrder();
        Optional<Order> optionalOrder = Optional.of(order);

        when(orderService.getOrderById(id)).thenReturn(optionalOrder);
        ResponseEntity<Optional<Order>> response = orderController.getOrderById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(optionalOrder, response.getBody());
    }

    @Test
    public void testUpdateOrder() {
        Order order = expectedOrder();
        when(orderService.updateOrder(any(Order.class))).thenReturn(order);
        ResponseEntity<Order> response = orderController.updateOrder(order);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(order, response.getBody());
        verify(orderService, times(1)).updateOrder(any(Order.class));
    }

    @Test
    public void testDeleteOrder() {

        long id =1L;
        doNothing().when(orderService).deleteOrder(id);

        ResponseEntity<String> response = orderController.deleteOrder(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Order Deleted Successfully", response.getBody());
        verify(orderService, times(1)).deleteOrder(1L);
    }

  

}
