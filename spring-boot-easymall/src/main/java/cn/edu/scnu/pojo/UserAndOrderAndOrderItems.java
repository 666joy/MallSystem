package cn.edu.scnu.pojo;

import java.util.List;

public class UserAndOrderAndOrderItems {
	private User user;
	private List<OrderAndOrderItems> ol;
	public UserAndOrderAndOrderItems(User user, List<OrderAndOrderItems> ol) {
		super();
		this.user = user;
		this.ol = ol;
	}
	public UserAndOrderAndOrderItems() {
		super();
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<OrderAndOrderItems> getOl() {
		return ol;
	}
	public void setOl(List<OrderAndOrderItems> ol) {
		this.ol = ol;
	}
	@Override
	public String toString() {
		return "UserAndOrderAndOrderItems [user=" + user + ", ol=" + ol + "]";
	}
	
	
}
