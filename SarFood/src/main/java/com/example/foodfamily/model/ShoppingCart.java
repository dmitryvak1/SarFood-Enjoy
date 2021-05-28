package com.example.foodfamily.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import com.example.foodfamily.entity.Product;

public class ShoppingCart implements Serializable {
	
	private static final long serialVersionUID = 1535770438453611801L;
	private Map<Long, ShoppingCartItem> products = new LinkedHashMap<>();
	private int totalCount = 0;
	private BigDecimal totalCost = BigDecimal.ZERO;

	public void addProduct(Product menuItem, int count) {
		ShoppingCartItem shoppingCartItem = products.get(menuItem.getId());
		if (shoppingCartItem == null) {
			shoppingCartItem = new ShoppingCartItem(menuItem, count);
			products.put(menuItem.getId(), shoppingCartItem);
		} else {
			shoppingCartItem.setCount(shoppingCartItem.getCount() + count);
		}
		refreshStatistics();
	}
	
	public void removeAll(Long idProduct) {
		ShoppingCartItem shoppingCartItem = products.get(idProduct);
		if (shoppingCartItem != null) {
			products.remove(idProduct);
		}
		refreshStatistics();
	}
	
	public void removeProduct(Long idProduct, int count) {
		ShoppingCartItem shoppingCartItem = products.get(idProduct);
		if (shoppingCartItem != null) {
			if (shoppingCartItem.getCount() > count) {
				shoppingCartItem.setCount(shoppingCartItem.getCount() - count);
			} else {
				products.remove(idProduct);
			}
			refreshStatistics();
		}
	}

	public Collection<ShoppingCartItem> getItems() {
		return products.values();
	}

	public int getTotalCount() {
		return totalCount;
	}
	
	public BigDecimal getTotalCost() {
		return totalCost;
	}
	
	private void refreshStatistics() {
		totalCount = 0;
		totalCost = BigDecimal.ZERO;
		for (ShoppingCartItem shoppingCartItem : getItems()) {
			totalCount += shoppingCartItem.getCount();
			totalCost = totalCost.add(shoppingCartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(shoppingCartItem.getCount())));
		}
	}
	@Override
	public String toString() {
		return String.format("ShoppingCart [products=%s, totalCount=%s, totalCost=%s]", products, totalCount, totalCost);
	}
}