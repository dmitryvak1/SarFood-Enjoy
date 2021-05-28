package com.example.foodfamily.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.foodfamily.Constants;
import com.example.foodfamily.model.ShoppingCart;
import com.example.foodfamily.service.OrderService;
import com.example.foodfamily.utils.SessionUtils;

@Controller
public class ShoppingCartController extends AbstractProductController {

	private static final long serialVersionUID = -4598507564716499904L;

	@Autowired
	OrderService orderService;

	@GetMapping("/shopping-cart")
	public String cartShow(HttpSession session, HttpServletRequest request) {

		ShoppingCart shoppingCart = getCurrentShoppingCart(request);
		request.setAttribute(Constants.CURRENT_SHOPPING_CART, shoppingCart);
		return "shopping-cart";
	}

	@PostMapping("/shopping-cart")
	public String removeProduct(@RequestParam(name = "productId", required = false) Long productId,
			HttpServletRequest request, HttpServletResponse response) {
		
		ShoppingCart shoppingCart = getCurrentShoppingCart(request);
		orderService.removeAllFromShoppingCart(productId, shoppingCart);
		if (shoppingCart.getItems().isEmpty()) {
			SessionUtils.clearCurrentShoppingCart(request, response);
		} else {
			String cookieValue = orderService.serializeShoppingCart(shoppingCart);
			SessionUtils.updateCurrentShoppingCartCookie(cookieValue, response);
		}
		return "shopping-cart";
	}
}
