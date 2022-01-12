package com.nic.san.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
//@ToString
@Entity
@Table
public class UserRole {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userRoleID;
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	
    private User user;
	@ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
	@JsonIgnore
	 private Role role;
    
}
