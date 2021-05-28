package com.example.foodfamily.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "order_item")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;

	@Column(name = "order_id")
	private Long idOrder;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "menu_id")
	private Product menuItem;

	private Integer count;

	public OrderItem() {
	}

	public OrderItem(Long idOrder, Product menuItem, Integer count) {

		this.idOrder = idOrder;
		this.menuItem = menuItem;
		this.count = count;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(Long idOrder) {
		this.idOrder = idOrder;
	}

	public Product getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(Product menuItem) {
		this.menuItem = menuItem;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", idOrder=" + idOrder + ", menuItem=" + menuItem + ", count=" + count + "]";
	}
}
