package org.java.sec;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class WebSecurity extends  WebSecurityConfigurerAdapter {
	//Bcrypt will generate automatic salt number for encoding of password
	@Bean
	public PasswordEncoder  passwordEncoder() {
		return new  BCryptPasswordEncoder();	
	}
	@Override
	protected void configure(HttpSecurity http) throws  Exception{
		http.csrf().disable();
		http.authorizeRequests()
		    .antMatchers("/","/home")
		    .permitAll();
		
    http.authorizeRequests().antMatchers("/admin/orderList", "/admin/order", "/admin/accountInfo")//
         .access("hasRole( 'ROLE_CHEF')");

  
   http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
   http.authorizeRequests().and().formLogin()
   .loginProcessingUrl("/check")
   .loginPage("admin/login")
   .defaultSuccessUrl("/admin/accountInfo")
   .failureUrl("/admin/login")
   .usernameParameter("userName")
   .passwordParameter("password")
   .and().logout().logoutUrl("/admin/logout").logoutSuccessUrl("/");
	}

	}


