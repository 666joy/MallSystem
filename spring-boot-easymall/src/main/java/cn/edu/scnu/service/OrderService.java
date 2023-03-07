package cn.edu.scnu.service;

import java.util.List;


import cn.edu.scnu.pojo.Order;
import cn.edu.scnu.pojo.OrderAndOrderItems;
import cn.edu.scnu.pojo.UserAndOrderAndOrderItems;
import cn.edu.scnu.vo.SysResult;

public interface OrderService {
	// ld写，添加订单
	public void save(String param,String userId);
	
	
	// ld写，查找订单
	public List<OrderAndOrderItems> query(String userId);
	
	// ld写，确认收货
	public void get(String orderId);

	//发货
	public void send(String orderId);
	
	// 订单管理-订单分类查找
	public String search(); 
}
