package com.kpit.cps.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class OrderTest {

    @Test
    public void testGettersAndSetters() {
        Order order = new Order();
        order.setId(1L);
        order.setOrderNumber("ORD123456");
        order.setTotalPrice(250.0);
        order.setStatus('1'); // Example status: '1' for Pending
        order.setCreatedBy(1L);
        order.setCreatedOn(LocalDateTime.of(2024, 8, 23, 14, 30));
        order.setUpdatedBy(1L);
        order.setUpdatedOn(LocalDateTime.of(2024, 8, 23, 15, 00));

        assertEquals(1L, order.getId());
        assertEquals("ORD123456", order.getOrderNumber());
        assertEquals(250.0, order.getTotalPrice());
        assertEquals('1', order.getStatus());
        assertEquals(1L, order.getCreatedBy());
        assertEquals(LocalDateTime.of(2024, 8, 23, 14, 30), order.getCreatedOn());
        assertEquals(1L, order.getUpdatedBy());
        assertEquals(LocalDateTime.of(2024, 8, 23, 15, 00), order.getUpdatedOn());
    }

}
