package cn.edu.scnu.pojo;

import java.util.List;

public class OrderAndOrderItems {
	private Order order;
	private List<OrderItem> orderItems;
	
	
	
	public OrderAndOrderItems(Order order, List<OrderItem> orderItems) {
		super();
		this.order = order;
		this.orderItems = orderItems;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	
}
