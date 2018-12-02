package org.java.model;

import java.util.ArrayList;
import java.util.List;



public class CartInformation {
	
	private int orderNum;
	 
    private CustomerInfo customerInfo;
 
    private final List<CartLineInformation> cartLines = new ArrayList<CartLineInformation>();
 
    public CartInformation() {
 
    }
 
    public int getOrderNum() {
        return orderNum;
    }
 
    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }
 
    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }
 
    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }
 
    public List<CartLineInformation> getCartLines() {
        return this.cartLines;
    }
 
    private CartLineInformation findLineByCode(String code) {
        for (CartLineInformation line : this.cartLines) {
            if (line.getProductInfo().getCode().equals(code)) {
                return line;
            }
        }
        return null;
    }
 
    public void addItem(ItemInfo itemInfo, int quantity) {
        CartLineInformation line = this.findLineByCode(itemInfo.getCode());
 
        if (line == null) {
            line = new CartLineInformation();
            line.setQuantity(0);
            line.setItemInfo(itemInfo);
            this.cartLines.add(line);
        }
        int newQuantity = line.getQuantity() + quantity;
        if (newQuantity <= 0) {
            this.cartLines.remove(line);
        } else {
            line.setQuantity(newQuantity);
        }
    }
 
    public void validate() {
 
    }
 
    public void updateProduct(String code, int quantity) {
        CartLineInformation line = this.findLineByCode(code);
 
        if (line != null) {
            if (quantity <= 0) {
                this.cartLines.remove(line);
            } else {
                line.setQuantity(quantity);
            }
        }
    }
 
    public void removeProduct(ItemInfo itemInfo) {
        CartLineInformation line = this.findLineByCode(itemInfo.getCode());
        if (line != null) {
            this.cartLines.remove(line);
        }
    }
 
    public boolean isEmpty() {
        return this.cartLines.isEmpty();
    }
 
    public boolean isValidCustomer() {
        return this.customerInfo != null && this.customerInfo.isValid();
    }
 
    public int getQuantityTotal() {
        int quantity = 0;
        for (CartLineInformation line : this.cartLines) {
            quantity += line.getQuantity();
        }
        return quantity;
    }
 
    public double getAmountTotal() {
        double total = 0;
        for (CartLineInformation line : this.cartLines) {
            total += line.getAmount();
        }
        return total;
    }
 
    public void updateQuantity(CartInformation cartForm) {
        if (cartForm != null) {
            List<CartLineInformation> lines = cartForm.getCartLines();
            for (CartLineInformation line : lines) {
                this.updateProduct(line.getProductInfo().getCode(), line.getQuantity());
            }
        }
 
    }

}
