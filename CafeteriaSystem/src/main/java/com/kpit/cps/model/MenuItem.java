package com.kpit.cps.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "menu_item")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private long price;

    @Column(name = "description")
    private String description;

    @Column(name = "available", nullable = false)
    private char available;

    @Column(name = "veg_flag", nullable = false)
    private char vegFlag;

    @Column(name = "active", nullable = false)
    private char active;

    @ManyToOne
    @JoinColumn(name = "menu_category_id", nullable = false)
    @JsonBackReference(value = "menu-category-menu-items")
    @ToString.Exclude
    private MenuCategory menuCategory;

    @ManyToOne
    @JoinColumn(name = "vendor_id", nullable = false)
    @JsonBackReference(value = "vendor-menu-items")
    @ToString.Exclude
    private Vendor vendor;

    @ManyToOne
    @JoinColumn(name = "serving_counter_id", nullable = false)
    @JsonBackReference(value = "serving-counter-menu-items")
    @ToString.Exclude
    private ServingCounter servingCounter;

    @Column(name = "created_by", nullable = false)
    private long createdBy;

    @Column(name = "created_on", nullable = false)
    private String createdOn;

    @Column(name = "updated_by", nullable = false)
    private long updatedBy;

    @Column(name = "updated_on", nullable = false)
    private String updatedOn;

    @OneToMany(mappedBy = "menuItem", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @ToString.Exclude
    private List<OrderItems> orderItems;

}

