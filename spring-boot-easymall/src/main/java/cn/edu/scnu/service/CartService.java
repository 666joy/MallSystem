package cn.edu.scnu.service;

import java.util.List;

import cn.edu.scnu.pojo.Cart;


public interface CartService {
	public Cart selectByProductIdAndUserId(String productId,String UserId);
	public void cartSave(Cart cart);
	public void cartSave(String productId,String userId);
	public List<Cart> cart(String userId);
	public void deleteCart(String productId,String userId);
	public void updateCart(String productId,String userId,Integer num);
	
	// 查看订单
	public List<Cart> query(String userId);
}
