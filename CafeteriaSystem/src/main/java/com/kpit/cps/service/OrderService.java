package com.kpit.cps.service;

import java.util.List;
import java.util.Optional;
import com.kpit.cps.model.Order;

public interface OrderService {

   public Order saveOrder(Order order);

   public Order updateOrder(Order order);

   public Optional<Order> getOrderById(long id);

   public List<Order> getAllOrders();

   public void deleteOrder(long id);

}
