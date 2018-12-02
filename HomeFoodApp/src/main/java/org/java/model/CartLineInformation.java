package org.java.model;



public class CartLineInformation {
	
	   private ItemInfo itemInfo;
	   private int quantity;
	  
	    public CartLineInformation() {
	        this.quantity = 0;
	    }
	  
	    public ItemInfo getProductInfo() {
	        return itemInfo;
	    }
	  
	    public void setItemInfo(ItemInfo itemInfo) {
	        this.itemInfo = itemInfo;
	    }
	  
	    public int getQuantity() {
	        return quantity;
	    }
	  
	    public void setQuantity(int quantity) {
	        this.quantity = quantity;
	    }
	  
	    public double getAmount() {
	        return this.itemInfo.getPrice() * this.quantity;
	    }
	     

}
