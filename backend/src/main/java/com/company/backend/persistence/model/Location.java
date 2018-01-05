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
@Table(name = "location")
public class Location {
	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Long id;
	
	@Column(name = "location")
	private String location;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Collection<Area_DS> getDs_areas() {
		return ds_areas;
	}

	public void setDs_areas(Collection<Area_DS> ds_areas) {
		this.ds_areas = ds_areas;
	}

	public Collection<Area_RTO> getRto_areas() {
		return rto_areas;
	}

	public void setRto_areas(Collection<Area_RTO> rto_areas) {
		this.rto_areas = rto_areas;
	}

	//@OneToMany(fetch=FetchType.EAGER)
	//@JoinTable(name ="location_DS_areas" ,joinColumns=@JoinColumn(name="location_id",referencedColumnName="Id"), inverseJoinColumns=@JoinColumn(name="area_ds_id" ,referencedColumnName="Id"))
	//@JoinColumn(name="ds_areas" ,nullable=false)
	@OneToMany(fetch=FetchType.LAZY,mappedBy="area")
	private Collection<Area_DS> ds_areas;
	
	
	//@JoinTable(name ="location_RTO_areas" ,joinColumns=@JoinColumn(name="location_id",referencedColumnName="Id"), inverseJoinColumns=@JoinColumn(name="area_rto_id" ,referencedColumnName="Id"))
	//@JoinColumn(name="rto_areas",nullable=false)
	@OneToMany(fetch=FetchType.LAZY,mappedBy="area")
	private Collection<Area_RTO> rto_areas;
	

}
