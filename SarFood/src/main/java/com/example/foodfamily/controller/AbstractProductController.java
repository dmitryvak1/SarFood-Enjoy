package com.example.foodfamily.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.foodfamily.model.ProductForm;
import com.example.foodfamily.model.ShoppingCart;
import com.example.foodfamily.service.OrderService;
import com.example.foodfamily.utils.SessionUtils;

public class AbstractProductController extends HttpServlet {

	private static final long serialVersionUID = 7880098522334685829L;
	
	@Autowired
	public OrderService orderService;

	@GetMapping("/")
	public String homePage() {
		return "redirect:menu";
	}
	@PostMapping("/")
	public String localization(@RequestParam(name = "lang", required = false) String locale, HttpSession session,
			HttpServletRequest request) {
		session.setAttribute("lang", locale);
		return "redirect:" + request.getHeader("referer");
	}

	public ShoppingCart getCurrentShoppingCart(HttpServletRequest req) {
		ShoppingCart shoppingCart = SessionUtils.getCurrentShoppingCart(req);
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCart();
		}
		return shoppingCart;
	}

	public void processProductForm(ProductForm form, ShoppingCart shoppingCart, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		orderService.addProductToShoppingCart(form, shoppingCart);
		String cookieValue = orderService.serializeShoppingCart(shoppingCart);
		SessionUtils.updateCurrentShoppingCartCookie(cookieValue, response);
	}

	public void sendResponse(ShoppingCart shoppingCart, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		JSONObject cardStatistics = new JSONObject();
		cardStatistics.put("totalCount", shoppingCart.getTotalCount());
		cardStatistics.put("totalCost", shoppingCart.getTotalCost());
		sendJSON(cardStatistics, request, response);
	}

	public static void sendJSON(JSONObject json, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json");
		resp.getWriter().println(json.toString());
		resp.getWriter().close();
	}
}
