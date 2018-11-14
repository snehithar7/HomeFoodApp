package org.java.form;

import org.java.entity.Item;

public class ItemForm {
	private String code;
    private String name;
    private double price;
    
    
    public ItemForm(){
    	
    }
    public ItemForm(Item item)

   {
    	this.code = item.getCode();
    	this.name = item.getName();
    	this.price =item.getPrice();
	
  }


	public String getCode() {

		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
    
    

}
