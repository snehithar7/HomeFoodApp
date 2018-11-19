package org.java.service;

import java.util.ArrayList;
import java.util.List;

import org.java.DAO.AccountD;
import org.java.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsImp implements UserDetailsService {
	@Autowired
	private AccountD  accountD;
	

    @Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
    	
    	Account account = accountD.findAccount(username);
    	System.out.println("Account=" +account);
		// TODO Auto-generated method stub
    	
    	if(account == null) {
    		throw new UsernameNotFoundException("User" +username+ "was not found in database");   		
    	}
    	
    	String role =account.getUserRole();
    	List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
    	GrantedAuthority  authority = new SimpleGrantedAuthority(role);
    	grantList.add(authority);
    	
    	boolean enabled =account.isActive();
    	boolean accountNonExpired = true;
    	boolean credentialsNonExpired = true;
    	boolean accountNonLocked = true;
    	
    	UserDetails userDetails  = (UserDetails) new User(account.getUserName(),
    			account.getPassword(),enabled,accountNonExpired, credentialsNonExpired,accountNonLocked,grantList);
    	
    			return userDetails;
	}

	
}
