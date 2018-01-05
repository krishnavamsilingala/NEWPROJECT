package com.company.backend.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.backend.persistence.model.Location;

public interface LocationRepository extends JpaRepository<Location,Long> {
	
	Location findByLocation(String location);

}
