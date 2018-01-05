package com.company.backend.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.backend.persistence.model.User;
import com.company.backend.registration.OnRegistrationCompleteEvent;
import com.company.backend.security.ISecurityUserService;
import com.company.backend.service.IUserService;
import com.company.backend.web.dto.UserDto;
import com.company.backend.web.util.GenericResponse;

@Controller
public class RegistrationController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ISecurityUserService securityUserService;
	
	@Autowired
    private ApplicationEventPublisher eventPublisher;
	
	@RequestMapping(value="user/register" , method= RequestMethod.POST)
	@ResponseBody
	public GenericResponse registerUserAcoount(  @Valid final UserDto accountDto ,final HttpServletRequest request) {
		
		final User registered= userService.registerNewUserAccount(accountDto);
		eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered,getAppUrl(request)));
		
		return new GenericResponse("success");
	}
	
	@RequestMapping(value="registrationconfirm" ,method=RequestMethod.GET)
	public String confirmRegistration(@RequestParam("token") final String token) {
		
		final String result= userService.validateVerificationToken(token);
		if (result.equals("valid")) {
			return "redirect:/registrationconfirmed";
		}
		
		return "redirect:/tokenexpired";
	}
	
	
	
	
	
	
	private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
		
	
	

}
