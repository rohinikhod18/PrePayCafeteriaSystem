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

import com.kpit.cps.model.Order;
import com.kpit.cps.service.OrderService;

@RestController
@RequestMapping("/cafeteriaapi/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    Logger logger= LoggerFactory.getLogger(OrderController.class);

    @PostMapping
       ResponseEntity<Order>createOrder(@RequestBody Order order){
             logger.info("Creating new order with data: {}", order);
             Order createdOrder =orderService.saveOrder(order);
             logger.info("Created order with ID: {}", order.getId());
             return new ResponseEntity<Order>(createdOrder,HttpStatus.CREATED);
       }

      @GetMapping
      public ResponseEntity<List<Order>> getAllOrders() {
             logger.info("Fetching all Orders");
             List<Order> listOfAllOrders = orderService.getAllOrders();
             logger.info("Fetched {} orders", listOfAllOrders.size());
             return new ResponseEntity<>(listOfAllOrders, HttpStatus.OK);
       }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Order>> getOrderById(@PathVariable("id") long id) {
            logger.info("Fetching order with ID: {}", id);
            Optional<Order> optionalOrder = orderService.getOrderById(id);
            logger.info("Fetched Order: {}", optionalOrder.get());
            return new ResponseEntity<>(optionalOrder, HttpStatus.OK);   
    }

       @PutMapping
       ResponseEntity<Order> updateOrder(@RequestBody Order order){
             logger.info("Updating Order with ID: {}",order.getId());
             Order updatedOrder = orderService.updateOrder(order);
             logger.info("Updated Order: {}", updatedOrder);
             return new ResponseEntity<Order>(updatedOrder, HttpStatus.OK); 
       }

       @DeleteMapping("/{id}")
       ResponseEntity<String> deleteOrder(@PathVariable("id")long id){
        logger.info("Deleting Order with ID: {}", id);
        orderService.deleteOrder(id);
        logger.info("Deleted Order with ID: {}", id);
        return new ResponseEntity<>("Order Deleted Successfully",HttpStatus.OK);

       }

}
