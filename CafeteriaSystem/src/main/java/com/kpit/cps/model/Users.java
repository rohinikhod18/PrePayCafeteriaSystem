package com.kpit.cps.model;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_name", nullable = false)
    private String userName;
    
    @ManyToOne
    @JoinColumn(name = "user_role_id", nullable = false)
    @JsonBackReference(value = "user-role-users")
    @ToString.Exclude
    private UserRole userRole;
    
    @Column(name = "device_id", nullable = false)
    private Integer device; 
    
    // @Column(name = "token_id", nullable = false)
    // private String token;
    
    @Column(name = "email", nullable = false)
    private String email;
    
    @Column(name = "phone", nullable = false)
    private String phoneNumber;
    
    @Column(name ="active", nullable = false)
    private boolean active;
    
    @Column(name = "created_by", nullable = false)
    private Long createdBy;

    @Column(name ="created_on", nullable = false)
    private String createdOn;

    @Column(name = "updated_by", nullable = false)
    private Long updatedBy;

    @Column(name ="updated_on", nullable = false)
    private String updatedOn;

    @ManyToMany
    @JoinTable(
    name = "vendor_users",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "vendor_id")   
    )
    @JsonManagedReference(value = "vendor-users")
    @ToString.Exclude
    private List<Vendor> vendorsList = new ArrayList<>();


}
