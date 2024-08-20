package com.kpit.cps.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderItemsDTO {

    private Long id;
    private Long orderId;
    private Long menuItemId;
    private double unitPrice;
    private double totalPrice;
    private int quantity;
    private String status;
    private Long createdBy;
    private Long updatedBy;
    private String createdOn;
    private String updatedOn;

}
