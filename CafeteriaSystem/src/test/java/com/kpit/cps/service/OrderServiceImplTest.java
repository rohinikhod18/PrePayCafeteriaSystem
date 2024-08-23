package com.kpit.cps.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

import com.kpit.cps.model.Order;
import com.kpit.cps.repository.OrderRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

     @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderServiceImpl;

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
    public void testSaveOrder() {
        Order order = expectedOrder();
        when(orderRepository.save(order)).thenReturn(order);
        Order savedOrder = orderServiceImpl.saveOrder(order);
        assertEquals(order.getId(), savedOrder.getId());
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    public void testUpdateOrder() {
        Order existingOrder = expectedOrder();
        Order updatedOrder = expectedOrder();
        updatedOrder.setTotalPrice(300.0);
        when(orderRepository.findById(existingOrder.getId())).thenReturn(Optional.of(existingOrder));
        when(orderRepository.save(updatedOrder)).thenReturn(updatedOrder);

        Order result = orderServiceImpl.updateOrder(updatedOrder);

        assertEquals(updatedOrder.getTotalPrice(), result.getTotalPrice());
        verify(orderRepository, times(1)).findById(existingOrder.getId());
        verify(orderRepository, times(1)).save(updatedOrder);
    }

    @Test
    public void testGetOrderById() {
        Order order = expectedOrder();
        long id = 1L;

        when(orderRepository.findById(id)).thenReturn(Optional.of(order));
        Optional<Order> result = orderServiceImpl.getOrderById(id);
        assertTrue(result.isPresent());
        assertEquals(order.getId(), result.get().getId());
        verify(orderRepository, times(1)).findById(id);
    }

    @Test
    public void testGetAllOrders() {
        List<Order> orders = new ArrayList<>();
        orders.add(expectedOrder());
        when(orderRepository.findAll()).thenReturn(orders);
        List<Order> result = orderServiceImpl.getAllOrders();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteOrder() {
        long id = 1L;
        Order order = expectedOrder();
        when(orderRepository.findById(id)).thenReturn(Optional.of(order));
        orderServiceImpl.deleteOrder(id);
        verify(orderRepository, times(1)).findById(id);
        verify(orderRepository, times(1)).deleteById(id);
    }

}
