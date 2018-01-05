package com.company.backend.persistence.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="DS_carTypes")
public class CarType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="carType")
	private String carType;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="carType")
	private Collection<DrivingSchools> dschools;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public Collection<DrivingSchools> getDschools() {
		return dschools;
	}

	public void setDschools(Collection<DrivingSchools> dschools) {
		this.dschools = dschools;
	}

}
