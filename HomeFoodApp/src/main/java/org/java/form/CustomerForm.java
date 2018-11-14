package org.java.form;

import org.java.model.CustomerInfo;

public class CustomerForm {

	private String name;
	private String email;
	private String address;
	private String phone;
	private Boolean valid;
	

public CustomerForm() {
	
}

public CustomerForm(CustomerInfo customerInfo)
{
	if(customerInfo!= null)
	{
		this.name = customerInfo.getName();
        this.address = customerInfo.getAddress();
        this.email = customerInfo.getEmail();
        this.phone = customerInfo.getPhone();
	}
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

public void setPhno(String phone) {
	this.phone = phone;
}

public Boolean isValid() {
	return valid;
}

public void setValid(Boolean valid) {
	this.valid = valid;
}


}

