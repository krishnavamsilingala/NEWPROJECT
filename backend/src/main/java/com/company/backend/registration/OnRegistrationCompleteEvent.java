package com.company.backend.registration;

import org.springframework.context.ApplicationEvent;

import com.company.backend.persistence.model.User;

@SuppressWarnings("serial")
public class OnRegistrationCompleteEvent extends ApplicationEvent {
	
	private final User user;
	private final String appUrl;
	
	public OnRegistrationCompleteEvent(final User user,final String appurl){
		super(user);
		this.user=user;
		this.appUrl=appurl;
		
	}
	
	public User getUser() {
		return user;
	}

	public String getAppUrl() {
		return appUrl;
	}

}
