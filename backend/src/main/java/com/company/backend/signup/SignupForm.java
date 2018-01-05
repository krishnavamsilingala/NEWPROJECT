package com.company.backend.signup;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.social.connect.UserProfile;

public class SignupForm {

	@NotEmpty
	private String socialid;

	@Size(min = 6, message = "must be at least 6 characters")
	private String password;

	@NotEmpty
	private String firstName;

	@NotEmpty
	private String lastName;
	
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String provider;

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getSocialid() {
		return socialid;
	}

	public void setSocialid(String socialid) {
		this.socialid=socialid;
		
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email=email;
	}

	public static SignupForm fromProviderUser(UserProfile providerUser,String provider,String socialid) {
		SignupForm form = new SignupForm();
		form.setFirstName(providerUser.getFirstName());
		form.setLastName(providerUser.getLastName());
		//rm.setUsername(providerUser.getUsername());
		form.setSocialid(socialid);
		form.setPassword("heybrothisispassord");
		form.setEmail(providerUser.getEmail());
		form.setProvider(provider);
		return form;
	}
}
