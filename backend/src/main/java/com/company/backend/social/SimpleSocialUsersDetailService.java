package com.company.backend.social;



import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialAuthenticationException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

import com.company.backend.persistence.dao.UserRepository;
import com.company.backend.persistence.model.Privilege;
import com.company.backend.persistence.model.Role;
import com.company.backend.persistence.model.User;

public class SimpleSocialUsersDetailService implements SocialUserDetailsService {

	
	@Autowired
	private UserRepository userRepository;



	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException, DataAccessException {
		
		System.out.println(userId);
		
		User user = userRepository.findBySocialid(userId);//.loadUserByUsername(userId);
		//if(user==null) {
			//throw new SocialAuthenticationException("no mapped user");
			
	//	}
		return new SocialUser(user.getSocialid(), user.getPassword(), getAuthorities(user.getRoles()));
	}
	
	final Collection<? extends GrantedAuthority> getAuthorities(final Collection<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private final List<String> getPrivileges(final Collection<Role> roles) {
        final List<String> privileges = new ArrayList<String>();
        final List<Privilege> collection = new ArrayList<Privilege>();
        for (final Role role : roles) {
            collection.addAll(role.getPrivileges());
        }
        for (final Privilege item : collection) {
            privileges.add(item.getName());
        }
        
        return privileges;
    }

    private final List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (final String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

}

