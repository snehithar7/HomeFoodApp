package org.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@RequestMapping("/")
	String index() {
		return "index";
		
	}
	
	 @RequestMapping("/403")
	   public String accessDenied() {
	      return "/403";
	   }
	 
	  
	   
	   @RequestMapping("/contactUs")
	   public String contact() {
	      return "contactUs";
	   }

}
