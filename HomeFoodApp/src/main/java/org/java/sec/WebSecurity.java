package org.java.sec;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class WebSecurity extends  WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws  Exception{
		http.authorizeRequests()
		    .antMatchers("/","/home")
		    .permitAll();
		
	}

}
