package com.kpit.cps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kpit.cps.model.OrderItems;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems,Long>{

}
