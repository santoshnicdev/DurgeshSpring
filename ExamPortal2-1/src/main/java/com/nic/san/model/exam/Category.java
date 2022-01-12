package com.nic.san.model.exam;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cid;
	private String descritption;
	private String title;
	@OneToMany(mappedBy = "category",fetch = FetchType.EAGER)
	@JsonIgnore
	Set<Quiz> quizes =new LinkedHashSet<Quiz>();

}
