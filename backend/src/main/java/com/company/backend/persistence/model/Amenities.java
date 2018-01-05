package com.company.backend.persistence.model;

import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="DS_Amenities")
public class Amenities {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="amenity_id")
	private Long id;
	
	@Column(name="amenityname")
	private String amenityname;
	
	//@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(fetch=FetchType.EAGER,mappedBy="amenities")
	private Set<DrivingSchools> dschools;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAmenityname() {
		return amenityname;
	}

	public void setAmenityname(String amenity) {
		this.amenityname = amenity;
	}

	public Set<DrivingSchools> getDschools() {
		return dschools;
	}

	public void setDschools(Set<DrivingSchools> dschools) {
		this.dschools = dschools;
	}
	
	

}
