package com.nic.san.model.exam;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Questions {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer qstID;
	@Column(length = 500)
	private String content;
	private String option1;
	private String option2;
	
	private String option3;
	private String option4;
	private String Image;
	private String answer;
	@ManyToOne
	private Quiz quiz;
	

}
