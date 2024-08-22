package com.kpit.cps.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "order_items")
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @JsonBackReference(value = "order-items")
    @ToString.Exclude
    private Order order;

    @ManyToOne
    @JoinColumn(name = "menu_item_id", nullable = false)
    @JsonBackReference(value = "menu-items")
    @ToString.Exclude
    private MenuItem menuItem;


    @Column(name = "unit_price", nullable = false)
    private double unitPrice; 

    @Column(name = "total_price", nullable = false)
    private double totalPrice; 

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "created_by", nullable = false)
    private Long createdBy;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "created_on", nullable = false)
    private String createdOn;

    @Column(name = "updated_on")
    private String updatedOn;
}

