package cn.edu.scnu.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cn.edu.scnu.mapper.OrderItemMapper;
import cn.edu.scnu.mapper.OrderMapper;
import cn.edu.scnu.mapper.UserMapper;
import cn.edu.scnu.pojo.Cart;
import cn.edu.scnu.pojo.Order;
import cn.edu.scnu.pojo.OrderAndOrderItems;
import cn.edu.scnu.pojo.OrderItem;
import cn.edu.scnu.pojo.Product;
import cn.edu.scnu.pojo.User;
import cn.edu.scnu.pojo.UserAndOrderAndOrderItems;
import cn.edu.scnu.vo.SysResult;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private CartService cartService;
	@Autowired
	private OrderItemMapper orderItemMapper;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserService userService;

	// 添加订单
	@Override
	public void save(String param, String userId) {
		
		// 查询该用户的购物车信息
		List<Cart> cartList = cartService.cart(userId);
		//将订单写入数据库，并且删除对应的购物车项目
		Date data = new Date();
			
		QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
		
		Double money=0.0;
		String orderId = UUID.randomUUID().toString();
		for(int i=0;i<cartList.size();i++) {
			// 写入t_order
			Timestamp timestamp = new Timestamp(data.getTime());
			// String orderId = UUID.randomUUID().toString();
			if(i==0) {
			
			}
			// 删除t_cart的对应记录
			cartService.deleteCart(cartList.get(i).getProductId(),userId);
			// 写入t_order_item
				// 获取t_order_item中的id最大值
			QueryWrapper<OrderItem> orederItemQueryWrapper = new QueryWrapper<>();
			orederItemQueryWrapper.orderByDesc("id");
			orederItemQueryWrapper.last("limit 1");
			OrderItem orderItemTemp = orderItemMapper.selectOne(orederItemQueryWrapper);
			Long orderItemId;
			if(orderItemTemp==null) {
				orderItemId = (long) 1;
			}else {
				orderItemId = orderItemTemp.getId()+1;
			}
			
			OrderItem orderItem = new OrderItem(orderItemId,orderId,cartList.get(i).getProductId(),cartList.get(i).getNum(),cartList.get(i).getProductImage(),cartList.get(i).getProductName(),cartList.get(i).getProductPrice());
			orderItemMapper.insert(orderItem);
			
				// 更新总金额
			money += cartList.get(i).getNum()*cartList.get(i).getProductPrice();
			
			// 商品的销售数量更新

			Product product = productService.selectProdById(cartList.get(i).getProductId());
			Integer saleNum = product.getProductSellnum();
			product.setProductSellnum(saleNum + cartList.get(i).getNum()); 
			productService.productUpdate(product);
			//QueryWrapper<Product> productQueryWrapper =  new QueryWrapper<>(); 
			//productQueryWrapper.eq(false, userId, orderId)
		}
		Timestamp timestamp = new Timestamp(data.getTime());
		Order order = new Order(orderId,money,param,1,timestamp,userId);
		orderMapper.insert(order);
		
	}

	// ld写，查找订单
	@Override
	public List<OrderAndOrderItems> query(String userId) {
		List<OrderAndOrderItems> res = new ArrayList<OrderAndOrderItems> ();
		 // 用user_id查t_order的记录
		QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
		orderQueryWrapper.eq("user_id", userId);
		//System.out.println("测试");
		List<Order> orderList = orderMapper.selectList(orderQueryWrapper);
		if(orderList.size()==0) {
			return null;
		}
		
		for(int i=0;i<orderList.size();i++) {
			// 用order_id查order_item的记录
			List<OrderItem> orderItemList = new ArrayList<OrderItem>();
	
			QueryWrapper<OrderItem> orderItemQueryWrapper = new QueryWrapper<>();
			orderItemQueryWrapper.eq("order_id", orderList.get(i).getOrderId());
			// orderItemList.add(orderItemMapper.selectOne(orderItemQueryWrapper));
			orderItemList= orderItemMapper.selectList(orderItemQueryWrapper);
						
			OrderAndOrderItems orderAndOrderItems = new OrderAndOrderItems( orderList.get(i) ,orderItemList);
					
			res.add(orderAndOrderItems);
		}
		

		return res;
	}

	// ld写，确认收货
	@Override
	public void get(String orderId) {
		QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
		orderQueryWrapper.eq("order_id", orderId);
		Order order = orderMapper.selectOne(orderQueryWrapper);
		order.setOrderPaystate(3);
		orderMapper.update(order, orderQueryWrapper);
		
	}
	//发货
	public void send(String orderId) {
		QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
		orderQueryWrapper.eq("order_id", orderId);
		Order order = orderMapper.selectOne(orderQueryWrapper);
		order.setOrderPaystate(2);
		orderMapper.update(order, orderQueryWrapper);
		
	}

	// 订单管理-订单分类查找
	@Override
	public String search() {
		List<UserAndOrderAndOrderItems> res = new ArrayList();
		List<User> userList =  userService.search();
		for(int i=0;i<userList.size();i++) {
			List<OrderAndOrderItems> oiList = this.query(userList.get(i).getUserId());
			UserAndOrderAndOrderItems temp = new UserAndOrderAndOrderItems(userList.get(i),oiList);
			res.add(temp);
		}
		
		return JSONObject.toJSONString(res);
	}
}
