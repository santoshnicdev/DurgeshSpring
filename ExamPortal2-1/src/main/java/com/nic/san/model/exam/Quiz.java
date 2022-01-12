package com.nic.san.model.exam;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Quiz {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long qid;
	private String qdescritption;
	private String qtitle;
	private Float maxMarks;
	private Integer  noOfQuestions;
	private Boolean active=false;
	@ManyToOne(fetch = FetchType.EAGER)
	Category category;
	@OneToMany(mappedBy = "quiz",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Questions> questions;
	
	

}
