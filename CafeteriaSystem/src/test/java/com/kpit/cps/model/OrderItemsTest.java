package com.kpit.cps.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class OrderItemsTest {

     @Test
    void testSettersGetters() {
        Order order = new Order(); 
        MenuItem menuItem = new MenuItem(); 

        OrderItems orderItems = new OrderItems();
        orderItems.setId(1L);
        orderItems.setOrder(order);
        orderItems.setMenuItem(menuItem);
        orderItems.setUnitPrice(100.0);
        orderItems.setTotalPrice(200.0);
        orderItems.setQuantity(2);
        orderItems.setStatus("CREATED");
        orderItems.setCreatedBy(1L);
        orderItems.setCreatedOn("2024-08-27");

        assertEquals(1L, orderItems.getId());
        assertEquals(order, orderItems.getOrder());
        assertEquals(menuItem, orderItems.getMenuItem());
        assertEquals(100.0, orderItems.getUnitPrice());
        assertEquals(200.0, orderItems.getTotalPrice());
        assertEquals(2, orderItems.getQuantity());
        assertEquals("CREATED", orderItems.getStatus());
        assertEquals(1L, orderItems.getCreatedBy());
        assertEquals("2024-08-27", orderItems.getCreatedOn());
        assertNull(orderItems.getUpdatedBy());
        assertNull(orderItems.getUpdatedOn());
    }

}
