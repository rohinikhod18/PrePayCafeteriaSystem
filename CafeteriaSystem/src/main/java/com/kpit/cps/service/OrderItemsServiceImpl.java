package com.kpit.cps.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpit.cps.dto.OrderItemsDTO;
import com.kpit.cps.exception.DataIsNotPresentException;
import com.kpit.cps.exception.IdNotFoundException;
import com.kpit.cps.model.MenuItem;
import com.kpit.cps.model.Order;
import com.kpit.cps.model.OrderItems;
import com.kpit.cps.repository.MenuItemRepository;
import com.kpit.cps.repository.OrderItemsRepository;
import com.kpit.cps.repository.OrderRepository;


@Service
public class OrderItemsServiceImpl implements OrderItemsService {

     Logger logger= LoggerFactory.getLogger(OrderItemsServiceImpl.class);

    @Autowired
    OrderItemsRepository orderItemsRepository;

     @Autowired
    private ModelMapper modelMapper;

    @Autowired
    OrderRepository orderRepository;
   
    @Autowired
    MenuItemRepository menuItemRepository;

    @Override
    public OrderItems saveOrderItems(OrderItemsDTO orderItemsDTO) {
        logger.info("Saving order items: {}", orderItemsDTO);

        OrderItems orderItems = modelMapper.map(orderItemsDTO, OrderItems.class);
        Optional<Order> optionalOrder = orderRepository.findById(orderItemsDTO.getOrderId());
        if (!optionalOrder.isPresent()) {
            throw new IdNotFoundException("Order not found with ID: " + orderItemsDTO.getOrderId());
        }
        orderItems.setOrder(optionalOrder.get());

        Optional<MenuItem> optionalMenuItem = menuItemRepository.findById(orderItemsDTO.getMenuItemId());
        if (!optionalMenuItem.isPresent()) {
            throw new IdNotFoundException("MenuItem not found with ID: " + orderItemsDTO.getMenuItemId());
        }
        orderItems.setMenuItem(optionalMenuItem.get());
        OrderItems savedOrderItems = orderItemsRepository.save(orderItems);
        logger.info("Saved order items with ID: {}", savedOrderItems.getId());
        return savedOrderItems;
    }

    @Override
    public OrderItems updateOrderItems(OrderItems orderItems) {
        logger.info("Updating order items with ID: {}", orderItems.getId());
        Optional<OrderItems> optionalOrderItems = orderItemsRepository.findById(orderItems.getId());
        if (!optionalOrderItems.isPresent()) {
            logger.warn("Order items not found with ID: {}", orderItems.getId());
            throw new IdNotFoundException("Order items not found with ID: " + orderItems.getId());
        }
        OrderItems updatedOrderItems = orderItemsRepository.save(orderItems);
        logger.info("Updated order items: {}", updatedOrderItems);
        return updatedOrderItems;
    }

    @Override
    public Optional<OrderItems> getOrderItemsById(long id) {
        logger.info("Fetching order items with ID: {}", id);
        Optional<OrderItems> optionalOrderItems = orderItemsRepository.findById(id);
        if (!optionalOrderItems.isPresent()) {
            logger.warn("Order items not found with ID: {}", id);
            throw new IdNotFoundException("Order items not found with ID: " + id);
        }
        logger.info("Fetched order items: {}", optionalOrderItems.get());
        return optionalOrderItems;
    }

    @Override
    public List<OrderItems> getAllOrderItems() {
       logger.info("Fetching all order items");
        List<OrderItems> orderItemsList = orderItemsRepository.findAll();
        if (orderItemsList.isEmpty()) {
            logger.warn("No order items found");
            throw new DataIsNotPresentException("No order items found");
        }
        logger.info("Fetched {} order items", orderItemsList.size());
        return orderItemsList;
    }

    @Override
    public void deleteOrderItems(long id) {
        logger.info("Deleting order items with ID: {}", id);
        Optional<OrderItems> optionalOrderItems = orderItemsRepository.findById(id);
        if (!optionalOrderItems.isPresent()) {
            logger.warn("Order items not found with ID: {}", id);
            throw new IdNotFoundException("Order items not found with ID: " + id);
        }
        orderItemsRepository.deleteById(id);
        logger.info("Deleted order items with ID: {}", id);
    }

}
