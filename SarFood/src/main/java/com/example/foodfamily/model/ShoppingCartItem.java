package com.example.foodfamily.model;

import java.io.Serializable;

import com.example.foodfamily.entity.Product;

public class ShoppingCartItem implements Serializable {

	private static final long serialVersionUID = 6436798264138502851L;
	private Product product;
	private int count;

	public ShoppingCartItem() {
	}

	public ShoppingCartItem(Product product, int count) {
		this.product = product;
		this.count = count;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "ShoppingCartItem [menuItem=" + product + ", count=" + count + "]";
	}
}