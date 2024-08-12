package com.kpit.cps.model;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;
    
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "serving_counter")
public class ServingCounter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "updated_by", nullable = false)
    private Long updatedBy;

    @Column(name = "updated_on", nullable = false)
    private String updatedOn;

    @Column(name = "created_by", nullable = false)
    private Long createdBy;

    @Column(name = "created_on", nullable = false)
    private String createdOn;

    @ManyToOne
    @JoinColumn(name = "vendor_id", nullable = false)
    @JsonBackReference
    @ToString.Exclude
    private Vendor vendor;
}
    

