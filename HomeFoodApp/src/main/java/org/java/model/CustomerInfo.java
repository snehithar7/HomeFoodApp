package org.java.model;

import org.java.form.CustomerForm;

public class CustomerInfo {
	
private String name;
private String email;
private String address;
private String phone;
private Boolean valid;

public CustomerInfo() {

}
public CustomerInfo(CustomerForm customerForm) {

	
	this.name = customerForm.getName();
	    this.address = customerForm.getAddress();
	    this.email = customerForm.getEmail();
	    this.phone = customerForm.getPhone();
	    this.valid = customerForm.isValid();
	    }


public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public Boolean isValid() {
	return valid;
}
public void setValid(Boolean valid) {
	this.valid = valid;
}
	 
}


