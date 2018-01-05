package com.company.backend.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class check {
	
	@RequestMapping(value="check" )
	public String checking() {
		System.out.println("redirecting to modal login");
		return "forward:/";
	}
	
	 @RequestMapping(value = {"","(modal:login)","(modal:signup)"})
	   private String home() {
	        return "forward:/";
	   }
	 @RequestMapping(value = {"/"})
	    private String home2() {
		 
	        return "/index.html";
	    }

}
