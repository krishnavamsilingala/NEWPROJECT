package com.company.backend.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.backend.persistence.model.Area_RTO;

public interface Area_RTO_Repository extends JpaRepository<Area_RTO,Long> {
	
	Area_RTO findByArea(String area);

}
