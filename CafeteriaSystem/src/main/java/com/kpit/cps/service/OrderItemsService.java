package com.kpit.cps.service;

import java.util.List;
import java.util.Optional;

import com.kpit.cps.dto.OrderItemsDTO;
import com.kpit.cps.model.OrderItems;

public interface OrderItemsService {

    public OrderItems saveOrderItems(OrderItemsDTO orderItemsdDto);

    public OrderItems updateOrderItems(OrderItems orderItems);

    public Optional<OrderItems> getOrderItemsById(long id);

    public List<OrderItems> getAllOrderItems();

    public void deleteOrderItems(long id);

}
