package com.example.foodfamily.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.foodfamily.Constants;
import com.example.foodfamily.model.ShoppingCart;

public class SessionUtils {

	private SessionUtils() {
	}

	public static ShoppingCart getCurrentShoppingCart(HttpServletRequest req) {
		return (ShoppingCart) req.getSession().getAttribute(Constants.CURRENT_SHOPPING_CART);
	}

	public static boolean isCurrentShoppingCartCreated(HttpServletRequest req) {
		return req.getSession().getAttribute(Constants.CURRENT_SHOPPING_CART) != null;
	}

	public static void setCurrentShoppingCart(HttpServletRequest req, ShoppingCart shoppingCart) {
		req.getSession().setAttribute(Constants.CURRENT_SHOPPING_CART, shoppingCart);
	}

	public static void clearCurrentShoppingCart(HttpServletRequest req, HttpServletResponse resp) {
		req.getSession().removeAttribute(Constants.CURRENT_SHOPPING_CART);
		WebUtils.setCookie(Constants.Cookie.SHOPPING_CART.getName(), null, 0, resp);
	}

	public static Cookie findShoppingCartCookie(HttpServletRequest req) {
		return WebUtils.findCookie(req, Constants.Cookie.SHOPPING_CART.getName());
	}

	public static void updateCurrentShoppingCartCookie(String cookieValue, HttpServletResponse resp) {
		WebUtils.setCookie(Constants.Cookie.SHOPPING_CART.getName(), cookieValue,
				Constants.Cookie.SHOPPING_CART.getTtl(), resp);
	}
}
