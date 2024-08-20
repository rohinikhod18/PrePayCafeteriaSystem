package com.kpit.cps.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kpit.cps.dto.OrderItemsDTO;
import com.kpit.cps.model.OrderItems;
import com.kpit.cps.service.OrderItemsService;


@RestController
@RequestMapping("/cafeteriaapi/orderitems")
public class OrderItemsController{

    @Autowired
    OrderItemsService orderItemsService;

    Logger logger= LoggerFactory.getLogger(OrderItemsController.class);

    @PostMapping
    public ResponseEntity<OrderItems> createOrderItem(@RequestBody OrderItemsDTO orderItemsDTO) {
        logger.info("Creating new order item with data: {}", orderItemsDTO);
        OrderItems createdOrderItem = orderItemsService.saveOrderItems(orderItemsDTO);
        logger.info("Created order item with ID: {}", createdOrderItem.getId());
        return new ResponseEntity<>(createdOrderItem, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrderItems>> getAllOrderItems() {
        logger.info("Fetching all order items");
        List<OrderItems> listOfAllOrderItems = orderItemsService.getAllOrderItems();
        logger.info("Fetched {} order items", listOfAllOrderItems.size());
        return new ResponseEntity<>(listOfAllOrderItems, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<OrderItems>> getOrderItemById(@PathVariable("id") long id) {
        logger.info("Fetching order item with ID: {}", id);
        Optional<OrderItems> optionalOrderItem = orderItemsService.getOrderItemsById(id);
        logger.info("Fetched OrderItem: {}", optionalOrderItem.get());
        return new ResponseEntity<>(optionalOrderItem, HttpStatus.OK);
       
    }

    @PutMapping
    public ResponseEntity<OrderItems> updateOrderItem(@RequestBody OrderItems orderItems) {
        logger.info("Updating order item with ID: {}", orderItems.getId());
        OrderItems updatedOrderItem = orderItemsService.updateOrderItems(orderItems);
        logger.info("Updated OrderItem: {}", updatedOrderItem);
        return new ResponseEntity<>(updatedOrderItem, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderItem(@PathVariable("id") long id) {
        logger.info("Deleting order item with ID: {}", id);
        orderItemsService.deleteOrderItems(id);
        logger.info("Deleted OrderItem with ID: {}", id);
        return new ResponseEntity<>("Order Item Deleted Successfully", HttpStatus.OK);
    }

 

}
