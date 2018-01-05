package com.company.backend.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.backend.persistence.model.Area_DS;

public interface Area_DS_Repository extends JpaRepository<Area_DS,Long> {

	
	Area_DS findByArea(String area);
}
