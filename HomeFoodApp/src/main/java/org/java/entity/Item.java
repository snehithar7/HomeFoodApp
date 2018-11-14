package org.java.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name ="Items")
public class Item implements Serializable{
	
	@Id
	@Column(name ="code",length =20, nullable=false)
	private String code;
	
	@Id
	@Column(name ="name",length =30, nullable=false)
	private String name;
	
	@Id
	@Column(name ="price",length =20, nullable=false)
	private double price;
	
	@Lob
	@Column(name ="image",nullable =false)
	private byte[] image;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date",nullable =false)
	private Date create_date;
	
	public Item() {
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	
	

}
