package com.kpit.cps.model;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "userrole")
public class UserRole {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "role_name", nullable = false)
	private String roleName;
	
	@Column(name = "is_designated", nullable = false)
	private String isDesigned;
	
	
	@Column(name = "created_by", nullable = false)
	private Integer createdBy;
 
	@Column(name ="created_on", nullable = false)
	private String createdOn;
 
 
	@Column(name = "updated_by", nullable = true)
	private Integer updatedBy;
 
 
	@Column(name ="updated_on", nullable = false)
	private String updatedOn;
   
	
	@JsonManagedReference(value = "user-role-users")
	@ToString.Exclude
	@OneToMany(mappedBy = "userRole",cascade = CascadeType.ALL)
	private List<Users> users = new ArrayList<Users>();;

}
