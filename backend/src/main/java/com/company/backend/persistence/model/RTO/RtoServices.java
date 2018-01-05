package com.company.backend.persistence.model.RTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rtoservices")
public class RtoServices {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String numberplate;
	
	private String maincategory;
	
	private String subcategory;
	
	private int days;
	
	private int rtovisits;
	
	private String provider;
	
	
}
