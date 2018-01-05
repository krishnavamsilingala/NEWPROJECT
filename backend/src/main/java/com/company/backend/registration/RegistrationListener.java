package com.company.backend.registration;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.company.backend.persistence.model.User;
import com.company.backend.service.IUserService;

public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private Environment env;
	
	
	public void onApplicationEvent(final OnRegistrationCompleteEvent Event) {
		
		this.confirmRegistration(Event);
	}
	
	public void confirmRegistration(final OnRegistrationCompleteEvent Event) {
		
		final User user=Event.getUser();
		final String token=UUID.randomUUID().toString();
		userService.createVerificationTokenForUser(user, token);
		
		final SimpleMailMessage email=constructEmailMessage(Event,user,token);
		mailSender.send(email);
		
	}
	
	public SimpleMailMessage constructEmailMessage(final OnRegistrationCompleteEvent event,final User user,final String token) {
		final String recipientAddress = user.getEmail();
        final String subject = "Registration Confirmation";
        final String confirmationUrl = event.getAppUrl() + "/registrationConfirm.html?token=" + token;
        final String message = "please click the below generated link to confirm Registration";
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " \r\n" + confirmationUrl);
        email.setFrom(env.getProperty("support.email"));
        return email;
	}
			

}
