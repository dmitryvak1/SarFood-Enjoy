package com.example.foodfamily.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.foodfamily.Constants;
import com.example.foodfamily.entity.Order;
import com.example.foodfamily.entity.User;
import com.example.foodfamily.exception.ResourceNotFoundException;
import com.example.foodfamily.model.ShoppingCart;
import com.example.foodfamily.service.OrderService;
import com.example.foodfamily.utils.SessionUtils;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService orderService;

	@GetMapping("{order}")
	public String orderGet(HttpServletRequest request, @AuthenticationPrincipal User user, @PathVariable Order order, Model model){
		try {
		model.addAttribute("order", orderService.findOrderById(order.getId(), user));
		} catch (NullPointerException e) {
			throw new ResourceNotFoundException("Order not found!");
		}
		System.out.println(order);
		return "order";
	}

	@PostMapping()
	public String orderPost(HttpServletRequest request, HttpServletResponse response,
			@AuthenticationPrincipal User user) {
		if (SessionUtils.isCurrentShoppingCartCreated(request)) {

			ShoppingCart shoppingCart = SessionUtils.getCurrentShoppingCart(request);
			Long idOrder = orderService.makeOrder(shoppingCart, user);
			Order order = orderService.findOrderById(idOrder, user);
			SessionUtils.clearCurrentShoppingCart(request, response);
			request.setAttribute(Constants.CURRENT_MESSAGE, "Order created successfully");
			request.setAttribute("order", order);
			return "order";
		} else {
			return "redirect:/menu";
		}
	}
}
