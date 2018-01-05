package com.company.backend.security;

import com.company.backend.persistence.dao.UserRepository;

import com.company.backend.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationProvider extends DaoAuthenticationProvider {
	
	 @Autowired
	    private UserRepository userRepository;
	 
	 @Override
	    public Authentication authenticate(Authentication auth) throws AuthenticationException {
	        
	        final User user = userRepository.findByEmail(auth.getName());
	        if (user == null) {
	        	System.out.println("#################################################################################"+auth.getName());
	        	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	            throw new BadCredentialsException("Invalid usernameeee or password");
	        }
	       	 final Authentication result = super.authenticate(auth);
	        return new UsernamePasswordAuthenticationToken(user, result.getCredentials(), result.getAuthorities());
	    }

	   

	    @Override
	    public boolean supports(Class<?> authentication) {
	        return authentication.equals(UsernamePasswordAuthenticationToken.class);
	    }

}
