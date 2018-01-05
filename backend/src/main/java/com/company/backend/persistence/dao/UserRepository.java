package com.company.backend.persistence.dao;

import com.company.backend.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    
    User findBySocialid(String socialid);

    @Override
    void delete(User user);

}
