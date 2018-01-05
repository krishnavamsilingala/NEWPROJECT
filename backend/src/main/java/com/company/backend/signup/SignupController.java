package com.company.backend.signup;


import java.util.Arrays;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.company.backend.persistence.dao.RoleRepository;
import com.company.backend.persistence.dao.UserRepository;
import com.company.backend.persistence.model.User;
import com.company.backend.service.UserService;
import com.company.backend.signin.SignInUtils;

@RestController
public class SignupController {

	private  UserRepository userRepository;
	private final ProviderSignInUtils providerSignInUtils;
	
	private  UserService userservice;
	@Autowired
	private  RoleRepository roleRepository;

	@Inject
	public SignupController(UserRepository userRepository, 
		                    ConnectionFactoryLocator connectionFactoryLocator,
		                    UsersConnectionRepository connectionRepository) {
		this.userRepository = userRepository;
		this.providerSignInUtils = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);
	}

	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public SignupForm signupForm(WebRequest request) {
		Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
		if (connection != null) {
			//request.setAttribute("message", new Message(MessageType.INFO, "Your " + StringUtils.capitalize(connection.getKey().getProviderId()) + " account is not associated with a Spring Social Showcase account. If you're new, please sign up."), WebRequest.SCOPE_REQUEST);
			request.setAttribute("message","hey bro" , WebRequest.SCOPE_REQUEST);
			
			return SignupForm.fromProviderUser(connection.fetchUserProfile(),connection.getKey().getProviderId(),connection.getKey().getProviderUserId());
		} else {
			return new SignupForm();
		}
	}

	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String signup(@Valid SignupForm form, BindingResult formBinding, WebRequest request) {
		if (formBinding.hasErrors()) {
			return null;
		}
		User user=new User();
		 user.setSocialid(form.getSocialid());
	        user.setFirstName(form.getFirstName());
	        user.setLastName(form.getLastName());
	        user.setEmail(form.getEmail());
	        user.setPassword(form.getPassword());
	        user.setEnabled(true);
	        user.setProvider(form.getProvider());
	        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_SOCIALUSER")));
	        user = userservice.registerNewSocialAccount(user);
		if (user != null) {
			SignInUtils.signin(user.getSocialid());
			providerSignInUtils.doPostSignUp(user.getSocialid(), request);
			return "redirect:/";
		}
		return "cannot bro";
	}

	// internal helpers
	
/*	private Account createAccount(SignupForm form, BindingResult formBinding) {
		try {
			Account account = new Account(form.getUsername(), form.getPassword(), form.getFirstName(), form.getLastName());
			accountRepository.createAccount(account);
			return account;
		} catch (UsernameAlreadyInUseException e) {
			formBinding.rejectValue("username", "user.duplicateUsername", "already in use");
			return null;
		}
	} */ 

}
