package com.kpit.cps.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpit.cps.exception.DataIsNotPresentException;
import com.kpit.cps.exception.IdNotFoundException;
import com.kpit.cps.model.Order;
import com.kpit.cps.repository.OrderRepository;


@Service
public class OrderServiceImpl implements OrderService{

    Logger logger= LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Order saveOrder(Order order) {
        logger.info("Saving order: {}", order);
        Order savedOrder= orderRepository.save(order);
        logger.info("Saved order with ID: {}", order.getId());   
        return savedOrder;
    }

    @Override
    public Order updateOrder(Order order) {
        logger.info("Updating order with ID: {}", order.getId());
        Optional<Order>optionalOrder= orderRepository.findById(order.getId());
         if (!optionalOrder.isPresent()) {
            logger.warn("Order not found with ID: {}", order.getId());
            throw new IdNotFoundException("order not found with ID: " + order.getId());
         }
         Order updatedOrder = orderRepository.save(order);
         logger.info("Updated order: {}", updatedOrder);
         return updatedOrder;
    }

    @Override
    public Optional<Order> getOrderById(long id) {
        logger.info("Fetching order with ID: {}", id);
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (!optionalOrder.isPresent()) {
            logger.warn("Order not found with ID: {}", id);
            throw new IdNotFoundException("Order not found with ID: " + id);
        }
        logger.info("Fetched order: {}");
        return optionalOrder;
    }

    @Override
    public List<Order> getAllOrders() {
        logger.info("Fetching all Orders");
        List<Order> orders = orderRepository.findAll();   
        if (orders.isEmpty()) {
            logger.warn("No Vendors found");
            throw new DataIsNotPresentException("No Orders found");
        }
       logger.info("Fetched {} Orders", orders.size()); 
       return orders;
    }

    @Override
    public void deleteOrder(long id) {
        logger.info("Deleting order with ID: {}", id);
        Optional<Order>optionalOrder= orderRepository.findById(id);
         if (!optionalOrder.isPresent()) {
            logger.warn("Order not found with ID: {}", id);
            throw new IdNotFoundException("Order not found with ID: " + id);
         }
         logger.warn("Order not found with ID: {}", id);
         orderRepository.deleteById(id);
    }

}
