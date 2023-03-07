package cn.edu.scnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.scnu.pojo.Cart;
import cn.edu.scnu.pojo.Order;
import cn.edu.scnu.pojo.OrderAndOrderItems;
import cn.edu.scnu.pojo.UserAndOrderAndOrderItems;
import cn.edu.scnu.service.OrderService;
import cn.edu.scnu.vo.SysResult;

@RestController
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(path="/order/save",params={"param","userId"})
	public SysResult save(String param,String userId){
		orderService.save(param,userId);
		
		return SysResult.ok();
	}
	
	// 订单查找，在myorder.html页面
	@RequestMapping(path="/order/query",params={"userId"})
	public List<OrderAndOrderItems> query(String userId){
		return orderService.query(userId);
	}
	
	//发货
	@RequestMapping(path="/order/send",params={"orderId"})
	public SysResult send(String orderId) {
		orderService.send(orderId);
		return SysResult.ok();
	}
	
	// 确认收货
	@RequestMapping(path="/order/get",params={"orderId"})
	public SysResult get(String orderId) {
		orderService.get(orderId);
		return SysResult.ok();
	}
	
	// 订单管理-订单分类查找
	@RequestMapping(path="/order/search")
	public String search(){
		return orderService.search();
	}
	
}
