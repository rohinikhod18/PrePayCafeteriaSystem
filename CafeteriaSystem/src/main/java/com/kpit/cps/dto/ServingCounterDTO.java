package com.kpit.cps.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ServingCounterDTO {
    private Long id;
    private String name;
    private boolean active;
    private Long updatedBy;
    private String updatedOn;
    private Long createdBy;
    private String createdOn;
    private Long vendorId;
}
