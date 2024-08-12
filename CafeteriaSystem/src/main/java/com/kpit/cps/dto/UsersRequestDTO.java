package com.kpit.cps.dto;
import java.util.List;

import com.kpit.cps.model.Vendor;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersRequestDTO {
    private Long id;
    private String userName;
    private Long userRoleId;
    private Integer device;
    private String email;
    private String phoneNumber;
    private boolean active;
    private Long createdBy;
    private String createdOn;
    private Long updatedBy;
    private String updatedOn;
    private List<Vendor> vendorsList; 


}
