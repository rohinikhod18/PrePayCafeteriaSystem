package com.kpit.cps.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MenuItemDTO {
    private Long id;
    private String name;
    private Long price;
    private String description;
    private char available;
    private char vegFlag;
    private char active;
    
    private Long menuCategoryId;
    private Long vendorId;
    private Long servingCounterId;

    private Long createdBy;
    private String createdOn;
    private Long updatedBy;
    private String updatedOn;

}
