package com.example.foodfamily.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;

	@Column(name = "user_id")
	private Long idUser;

	@Transient
	private List<OrderItem> orderItems;

	private Timestamp created;

	public Order() {

	}

	public Order(Long idUser, Timestamp created) {
		super();
		this.idUser = idUser;
		this.created = created;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public BigDecimal getTotalCost() {

		BigDecimal cost = BigDecimal.ZERO;
		if (orderItems != null) {
			for (OrderItem orderItem : orderItems) {
				cost = cost.add(orderItem.getMenuItem().getPrice().multiply(BigDecimal.valueOf(orderItem.getCount())));
			}
		}
		return cost;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", idUser=" + idUser + ", orderItems=" + orderItems + ", created=" + created + ", cost=" + getTotalCost() +"]";
	}
}
