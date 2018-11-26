package org.java.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
@Entity
@Table(name ="Orders", uniqueConstraints = {@UniqueConstraint (columnNames ="orderNum")})
public class Order implements Serializable {

private long id;
@Id
@Column(name = "Id",length = 50)
private String  Id;

@Column(name = "OrderDate", nullable = false)
private Date OrderDate;

@Column(name = "OrderNum",nullable = false)
private String  OrderNum;

@Column(name = "Amount",nullable = false)
private double  Amount;

@Column(name = "Customername",length = 300,nullable = false)
private String Customername;

@Column(name = "Customeraddress",length =350,nullable = false)
private String  Customeraddress;

@Column(name = "CustomerPhone",length = 300,nullable = false)
private String CustomerPhone;

@Column(name = "Customeremail",length = 290,nullable = false)
private String Customeremail;

public String getId() {
	return Id;
}

public void setId(String id) {
	Id = id;
}

public Date getOrderDate() {
	return OrderDate;
}

public void setOrderDate(Date orderDate) {
	OrderDate = orderDate;
}

public String getOrderNum() {
	return OrderNum;
}

public void setOrderNum(String orderNum) {
	OrderNum = orderNum;

}

public double getAmount() {
	return Amount;
}

public void setAmount(double amount) {
	Amount = amount;
}

public String getCustomername() {
	return Customername;
}

public void setCustomername(String customername) {
	Customername = customername;
}

public String getCustomeraddress() {
	return Customeraddress;
}

public void setCustomeraddress(String customeraddress) {
	Customeraddress = customeraddress;
}

public String getCustomerPhone() {
	return CustomerPhone;
}

public void setCustomerPhone(String customerPhone) {
	CustomerPhone = customerPhone;
}

public String getCustomeremail() {
	return Customeremail;
}

public void setCustomeremail(String customeremail) {
	Customeremail = customeremail;
}





}
