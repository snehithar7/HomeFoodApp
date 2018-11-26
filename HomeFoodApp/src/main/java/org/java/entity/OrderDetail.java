package org.java.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name = "Order_details")
public class OrderDetail{

private String id;
private  Order order;
private Item item;;
	
 @Column(name = "Quanity", nullable = false)
 private int quanity;
	 
 @Column(name = "Price", nullable = false)
 private double price;
	 
 @Column(name = "Amount", nullable = false)
 private double amount;

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public int getQuanity() {
	return quanity;
}

public void setQuanity(int quanity) {
	this.quanity = quanity;
}

public double getPrice() {
	return price;
}

public void setPrice(double price) {
	this.price = price;
}

public double getAmount() {
	return amount;
}

public void setAmount(double amount) {
	this.amount = amount;
}

}
