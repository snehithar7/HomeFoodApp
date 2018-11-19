package org.java.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="Accounts")
public class Account implements Serializable{

	private static final long serialVersionUID = 4L;
	
public static final String ROLE_ADMIN ="ADMIN";
public static final String ROLE_CHEF ="CHEF";

@Id
@Column(name ="User_Name",length =20, nullable =false)
private String userName;

@Id
@Column(name ="PassWord",length =128, nullable =false)
private String Password;

@Id
@Column(name ="Role" , length =20,nullable =false)
private String userRole;
@Id
@Column(name ="Active", length =1,nullable =false)
private boolean Active;

public boolean isActive() {
	return Active;
}

public void setActive(boolean active) {
	Active = active;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getPassword() {
	return Password;
}

public void setPassword(String password) {
	Password = password;
}

public String getUserRole() {
	return userRole;
}

public void setUserRole(String userRole) {
	this.userRole = userRole;
}

public String toString() {
	return "[" +this.userName+ "," +this.Password+ ","+this.userRole +"]";
}
}
