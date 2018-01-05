package com.company.backend.social;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Component;

import com.company.backend.persistence.dao.RoleRepository;
import com.company.backend.persistence.dao.UserRepository;
import com.company.backend.persistence.model.User;





@Component
public class AppConnectionSignUp implements ConnectionSignUp {
	
	 @Autowired
	    private RoleRepository roleRepository;

	@Autowired
	 private UserRepository userRepository;
	 
	
	
	
	 	@Override
	    public String execute(final Connection<?> connection) {
	        User user=new User();
	     
	        
	        final UserProfile userProfile=connection.fetchUserProfile();
	        
	        user.setSocialid(connection.getKey().getProviderUserId());
	        user.setFirstName(userProfile.getFirstName());
	        user.setLastName(userProfile.getLastName());
	        user.setEmail(userProfile.getEmail());
	        user.setPassword("password");
	        user.setEnabled(true);
	        user.setProvider(connection.getKey().getProviderId());
	        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_SOCIALUSER")));

		    System.out.println("***************************************************************************"
		       		+ "********************************************");
		    user=userRepository.save(user);
	       return  user.getSocialid();
	    }
	 

}

