/*package com.company.backend.security;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialAuthenticationException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

import com.company.backend.persistence.model.SocialUser;



@Service("socialUserDetailService")
public class SocialUserDetailService implements SocialUserDetailsService {

   
	@Inject
    @Qualifier(value = "userDetailsService")
	private UserDetailsService userDetailService;
	
    @Override
    public SocialUserDetails loadUserByUserId(final String userId) throws UsernameNotFoundException, DataAccessException {
        User user =  (User)userDetailService.loadUserByUsername(userId);
        if (user == null) {
            throw new SocialAuthenticationException("No local user mapped with social user " + userId);
        }
        return new SocialUser("ID",user.getUsername(), user.getPassword(), user.isEnabled(), true,true,true, user.getAuthorities());
    }
}
*/
