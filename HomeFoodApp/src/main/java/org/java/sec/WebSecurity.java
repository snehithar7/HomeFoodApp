package org.java.sec;


import org.java.service.UserDetailsImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 
@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter {
 
   @Autowired
   UserDetailsImp userDetailsService;
 
   @Bean
   public BCryptPasswordEncoder passwordEncoder() {
      BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
      return bCryptPasswordEncoder;
   }
 
   @Autowired
   public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
 
      // Setting Service to find User in the database.
      // And Setting PassswordEncoder
      auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
 
   }
 
   @Override
   protected void configure(HttpSecurity http) throws Exception {
 
      http.csrf().disable();
 
      
      http.authorizeRequests().antMatchers("/admin/orderList", "/admin/order", "/admin/accountInfo","/admin/pListe","/admin/")//
            .access("hasRole('ROLE_CHEF')");
 
      
      http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
 
      // Configuration for Login Form.
      http.authorizeRequests().and().formLogin()//
 
            //
            .loginProcessingUrl("/j_spring_security_check") // Submit URL
            .loginPage("/admin/login")//
            .defaultSuccessUrl("/")//
            .failureUrl("/admin/login?error=true")//
            .usernameParameter("userName")//
            .passwordParameter("password")
 
            // Configuration for the Logout page.
            // (After logout, go to home page)
            .and().logout().logoutUrl("/admin/logout").logoutSuccessUrl("/");
 
   }
}