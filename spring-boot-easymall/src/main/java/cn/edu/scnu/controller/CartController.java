package cn.edu.scnu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/*
 * ld创建，用来接收加入购物车的请求
 * 
 */


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.scnu.pojo.Cart;
import cn.edu.scnu.service.CartService;
import cn.edu.scnu.vo.SysResult;

@RestController
public class CartController {
	@Autowired
	private CartService cartService;
	
	@RequestMapping(path="/cart/save",params={"productId","userId","num"})
	public SysResult saveCart(String productId,String userId){
		Cart cart1 = cartService.selectByProductIdAndUserId(productId, userId);
		if(cart1 == null) {
			cartService.cartSave(productId,userId);
			System.out.println("添加成功");
		}else {
			System.out.println("商品已在购物车");
		}
		
		System.out.println(productId);
		System.out.println(userId);
		
		return SysResult.ok();
	}
	
	@RequestMapping(path="/cart/",params={"userId"})
	public List<Cart> cart(String userId){
		return 	cartService.cart(userId);
	}
	
	@RequestMapping(path="/cart/delete",params={"productId","userId"})
	public SysResult deleteCart(String productId,String userId){
		cartService.deleteCart(productId,userId);
		return SysResult.ok();
	}
	
	@RequestMapping(path="/cart/update",params={"productId","userId","num"})
	public SysResult updateCart(String productId,String userId,Integer num){
		cartService.updateCart(productId,userId,num);
		return SysResult.ok();
	}
	
	// 订单显示 my-order.html界面的
	@RequestMapping(path="/cart/query/",params={"userId"})
	public List<Cart> query(String userId){
		return 	cartService.cart(userId);
	}
	
}
