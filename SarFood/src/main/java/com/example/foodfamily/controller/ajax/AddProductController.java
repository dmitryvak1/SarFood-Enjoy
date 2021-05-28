package com.example.foodfamily.controller.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.foodfamily.controller.AbstractProductController;
import com.example.foodfamily.model.ProductForm;
import com.example.foodfamily.model.ShoppingCart;
import com.example.foodfamily.service.OrderService;
import com.example.foodfamily.utils.SessionUtils;

@RestController
@RequestMapping("/menu")
public class AddProductController extends AbstractProductController {

	private static final long serialVersionUID = -2040420341335956265L;

	@Autowired
	public OrderService orderService;

	@PostMapping(value = "/save")
	public void postCustomer(@RequestBody ProductForm productForm, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ShoppingCart shoppingCart = getCurrentShoppingCart(request);
		processProductForm(productForm, shoppingCart, request, response);
		if (!SessionUtils.isCurrentShoppingCartCreated(request)) {
			SessionUtils.setCurrentShoppingCart(request, shoppingCart);
		}
		// Create Response Object
		sendResponse(shoppingCart, request, response);
	}
}