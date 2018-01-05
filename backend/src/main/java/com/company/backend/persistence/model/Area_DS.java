package com.company.backend.persistence.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name ="Areas_DS")
public class Area_DS {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="area")
	private String area;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="location",nullable=false)
	private Location area_location;
	
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="area")
	private Collection<DrivingSchools> drivingschools;
	


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getArea() {
		return area;
	}


	public void setArea(String area) {
		this.area = area;
	}


	public Location getArea_location() {
		return area_location;
	}


	public void setArea_location(Location area_location) {
		this.area_location = area_location;
	}
	

}
