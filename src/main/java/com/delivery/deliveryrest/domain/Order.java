package com.delivery.deliveryrest.domain;

import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Formula;
import org.springframework.data.annotation.CreatedDate;

import lombok.Data;

@Data
@Entity
@DynamicUpdate
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(updatable = false)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<OrderItem> items = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	private Restaurant restaurant;

	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	@Enumerated(value = EnumType.STRING)
	private OrderStatus status = OrderStatus.PENDING;

	@Formula("(select sum(i.total) from orders o inner join orders_items oi on oi.order_id=o.id inner join order_item i on i.id=oi.items_id)")
	private double total;

	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private Timestamp createdAt;
}
