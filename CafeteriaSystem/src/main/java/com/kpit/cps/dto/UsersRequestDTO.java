package com.kpit.cps.dto;
import com.kpit.cps.model.UserRole;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersRequestDTO {
    private Long id;
    private String userName;
    private UserRole userRole;
    private Integer device;
    private String email;
    private String phoneNumber;
    private boolean active;
    private Long createdBy;
    private String createdOn;
    private Long updatedBy;
    private String updatedOn;

}
