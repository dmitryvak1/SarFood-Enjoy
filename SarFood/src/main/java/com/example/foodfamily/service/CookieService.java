package com.example.foodfamily.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.foodfamily.model.ProductForm;
import com.example.foodfamily.model.ShoppingCartItem;

@Service
public class CookieService {
	
	public String createShoppingCartCookie(Collection<ShoppingCartItem> items) {
		StringBuilder res = new StringBuilder();
		for (ShoppingCartItem item : items) {
			res.append(item.getProduct().getId()).append("-").append(item.getCount()).append("|");
		}
		if (res.length() > 0) {
			res.deleteCharAt(res.length() - 1);
		}
		return res.toString();
	}
	
	public List<ProductForm> parseShoppingCartCookie(String cookieValue) {
		List<ProductForm> products = new ArrayList<ProductForm>();
		String[] items = cookieValue.split("\\|");
		for (String item : items) {
			try {
				String data[] = item.split("-");
				long idProduct = Integer.parseInt(data[0]);
				int count = Integer.parseInt(data[1]);
				products.add(new ProductForm(idProduct, count));
			} catch (RuntimeException e) {
				System.out.printf("Can't parse cookie value: item=" + item+", cookieValue="+cookieValue, e);
			}
		}
		return products;
	}
}
