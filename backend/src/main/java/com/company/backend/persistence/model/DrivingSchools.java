package com.company.backend.persistence.model;

import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="DrivingSchools")
public class DrivingSchools {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ds_id")
	private Long id;
	

	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="area",nullable=false)
	private Area_DS area;
	
	@Column(name="address")
	private String address;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="carType",nullable=false)
	private CarType carType;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="servicep_id",nullable=false)
	private ServiceProvider serviceProvider;
	
	private String serviceObject;
	
	//@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "ds_features2", joinColumns = @JoinColumn(name = "ds_id", referencedColumnName = "ds_id"), inverseJoinColumns = @JoinColumn(name = "feature_id", referencedColumnName = "feature_id"))
	private Set<Features> features;
	
	//@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "ds_amenities2", joinColumns = @JoinColumn(name = "ds_id", referencedColumnName = "ds_id"), inverseJoinColumns = @JoinColumn(name = "amenity_id", referencedColumnName = "amenity_id"))
	private Set<Amenities> amenities;
	
	private int photos;
	
	private int numOfClasses;
	 
	private String durtionOfClass;
	
	private boolean enabled;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Area_DS getArea() {
		return area;
	}

	public void setArea(Area_DS area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public CarType getCarType() {
		return carType;
	}

	public void setCarType(CarType carType) {
		this.carType = carType;
	}

	public ServiceProvider getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(ServiceProvider serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public String getServiceObject() {
		return serviceObject;
	}

	public void setServiceObject(String serviceObject) {
		this.serviceObject = serviceObject;
	}

	public Set<Features> getFeatures() {
		return features;
	}

	public void setFeatures(Set<Features> features) {
		this.features = features;
	}

	public Set<Amenities> getAmenities() {
		return amenities;
	}

	public void setAmenities(Set<Amenities> amenities) {
		this.amenities = amenities;
	}

	public int getPhotos() {
		return photos;
	}

	public void setPhotos(int photos) {
		this.photos = photos;
	}

	public int getNumOfClasses() {
		return numOfClasses;
	}

	public void setNumOfClasses(int numOfClasses) {
		this.numOfClasses = numOfClasses;
	}

	public String getDurtionOfClass() {
		return durtionOfClass;
	}

	public void setDurtionOfClass(String durtionOfClass) {
		this.durtionOfClass = durtionOfClass;
	}
	
	
	

}
