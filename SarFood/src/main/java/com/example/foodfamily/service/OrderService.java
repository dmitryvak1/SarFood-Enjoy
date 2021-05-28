package com.example.foodfamily.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.foodfamily.entity.Order;
import com.example.foodfamily.entity.OrderItem;
import com.example.foodfamily.entity.Product;
import com.example.foodfamily.entity.Role;
import com.example.foodfamily.entity.User;
import com.example.foodfamily.exception.AccessDeniedException;
import com.example.foodfamily.exception.InternalServerErrorException;
import com.example.foodfamily.exception.ResourceNotFoundException;
import com.example.foodfamily.model.ProductForm;
import com.example.foodfamily.model.ShoppingCart;
import com.example.foodfamily.model.ShoppingCartItem;
import com.example.foodfamily.repository.OrderItemRepository;
import com.example.foodfamily.repository.OrderRepository;
import com.example.foodfamily.repository.ProductRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CookieService cookieService;
	
	@Transactional(readOnly=true)
	public void addProductToShoppingCart(ProductForm productForm, ShoppingCart shoppingCart) {
		Product product = productRepository.findOne(productForm.getIdProduct());
		if(product == null) {
			throw new InternalServerErrorException("Product not found by id = " + productForm.getIdProduct());
		}
		shoppingCart.addProduct(product, productForm.getCount());
	}
	
	public void removeProductFromShoppingCart(ProductForm form, ShoppingCart shoppingCart) {
		shoppingCart.removeProduct(form.getIdProduct(), form.getCount());
	}
	
	public void removeAllFromShoppingCart(Long id, ShoppingCart shoppingCart) {
		shoppingCart.removeAll(id);
	}
	
	public String serializeShoppingCart(ShoppingCart shoppingCart) {
		return cookieService.createShoppingCartCookie(shoppingCart.getItems());
	}

	@Transactional(readOnly=true)
	public ShoppingCart deserializeShoppingCart(String cookieValue) {
		List<ProductForm> products = cookieService.parseShoppingCartCookie(cookieValue);
		ShoppingCart shoppingCart = new ShoppingCart();
		for(ProductForm productForm : products) {
			try {
				addProductToShoppingCart(productForm, shoppingCart);
			} catch (RuntimeException e) {
				System.err.printf("Can't add product to ShoppingCart: productForm = " + productForm, e);
			}
		}
		return shoppingCart.getItems().isEmpty() ? null : shoppingCart;
	}
	
	@Transactional
	public long makeOrder(ShoppingCart shoppingCart, User user) {
		
		final Order order = new Order(user.getId(), new Timestamp(System.currentTimeMillis()));
		orderRepository.save(order);
		order.setOrderItems(new ArrayList<>());
		
		for (ShoppingCartItem item : shoppingCart.getItems()) {
			OrderItem orderItem = new OrderItem(order.getId(), item.getProduct(), item.getCount());
			orderItemRepository.save(orderItem);
			order.getOrderItems().add(orderItem);
		}
		return order.getId();
	}
	
	@Transactional(readOnly=true)
	public Order findOrderById (Long id, User user) {
		Order order = orderRepository.findOne(id);
		if (order == null) {
			throw new ResourceNotFoundException("Order not found by id: " + id);
		}
		if (!order.getIdUser().equals(user.getId()) && !user.getRoles().contains(Role.ADMIN)) {
			throw new AccessDeniedException("Account with id = " + user.getId() + " is not owner for order with id = " + id);
		}
		order.setOrderItems(orderItemRepository.findByIdOrder(id));
		return order;
	}
	
	@Transactional(readOnly=true)
	public Page<Order> listMyOrders(User user, Pageable pageable) {
		return orderRepository.findByIdUser(user.getId(), pageable);
	}
    public Page<Order> findAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }
}
