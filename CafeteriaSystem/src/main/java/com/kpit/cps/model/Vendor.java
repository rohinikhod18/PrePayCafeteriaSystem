package com.kpit.cps.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "vendor")
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_id", nullable = false)
	private long companyId;

    @Column(name = "vendor_name", nullable = false)
    private String vendorName;

    @Column(name = "contact_first_name", nullable = false)
    private String contactFirstName;

    @Column(name = "contact_last_name", nullable = false)
    private String contactLastName;

    @Column(name = "contact_email", nullable = false)
    private String contactEmail;

    @Column(name = "contact_mobile", nullable = false)
    private long contactMobile;

    @Column(name = "is_designated", nullable = false)
	private String isDesigned;

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

    @ManyToMany(mappedBy = "vendorsList")
   // @JsonBackReference
   //@jsonignore
     @JsonBackReference(value = "vendor-users")
    @ToString.Exclude
    private List<Users> usersList = new ArrayList<>();
  
    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "vendor-serving-counters")
    @ToString.Exclude
    private List<ServingCounter> servingCounters = new ArrayList<>();

    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "vendor-menu-items")
    @ToString.Exclude
    private List<MenuItem> menuItems = new ArrayList<>();
    
}
