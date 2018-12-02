package org.java.form;

import org.java.entity.Item;
import org.springframework.web.multipart.MultipartFile;

public class ItemForm {
	private String code;
    private String name;
    private double price;
    
    
    private boolean newProduct = false;
    
    // Upload file.
    private MultipartFile fileData;
 
    public ItemForm() {
        this.newProduct= true;
    }
 
    public ItemForm(Item item) {
        this.code = item.getCode();
        this.name = item.getName();
        this.price = item.getPrice();
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
 
    public MultipartFile getFileData() {
        return fileData;
    }
 
    public void setFileData(MultipartFile fileData) {
        this.fileData = fileData;
    }
 
    public boolean isNewProduct() {
        return newProduct;
    }
 
    public void setNewProduct(boolean newProduct) {
        this.newProduct = newProduct;
    }
 
}