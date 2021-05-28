package com.example.foodfamily.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.foodfamily.entity.Order;
import com.example.foodfamily.entity.Role;
import com.example.foodfamily.entity.User;
import com.example.foodfamily.service.OrderService;

@Controller
public class OrdersListContoller {

	@Autowired
	OrderService orderService;

	@GetMapping("/orders-list")
	String showList(@AuthenticationPrincipal User user, HttpServletRequest request, Model model,
			@PageableDefault(size = 4, direction = Sort.Direction.DESC) Pageable pageable) {

		Page<Order> page = null;

		if (user.getRoles().contains(Role.ADMIN)) {
			page = orderService.findAllOrders(pageable);
		} else {
			page = orderService.listMyOrders(user, pageable);
		}

		for (Order order : page) {
			request.setAttribute("order", orderService.findOrderById(order.getId(), user));
		}
		request.setAttribute("page", page);
		model.addAttribute("url", "/orders-list");
		return "orders-list";
	}
}
