package com.kpit.cps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kpit.cps.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o.orderNumber FROM Order o WHERE o.orderNumber LIKE :datePrefix% ORDER BY o.orderNumber DESC")
    String findLastOrderNumberForDate(@Param("datePrefix") String datePrefix);
}

