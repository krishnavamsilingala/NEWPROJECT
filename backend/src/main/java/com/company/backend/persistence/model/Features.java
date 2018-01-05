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

@Entity
@Table(name="DS_Features")
public class Features {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="feature_id")
	private Long id;
	
	@Column(name="featurename")
	private String featurename;
	
//	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(fetch=FetchType.EAGER,mappedBy="features")
	private Set<DrivingSchools> dschools;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFeaturename() {
		return featurename;
	}

	public void setFeaturename(String featurename) {
		this.featurename = featurename;
	}

	public Set<DrivingSchools> getDschools() {
		return dschools;
	}

	public void setDschools(Set<DrivingSchools> dschools) {
		this.dschools = dschools;
	}
	
	
}
