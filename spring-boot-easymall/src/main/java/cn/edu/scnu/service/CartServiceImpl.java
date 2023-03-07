package cn.edu.scnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cn.edu.scnu.mapper.CartMapper;
import cn.edu.scnu.mapper.ProductMapper;
import cn.edu.scnu.pojo.Cart;
import cn.edu.scnu.pojo.Product;

@Service
public class CartServiceImpl implements CartService{
	@Autowired
	private CartMapper cartMapper;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public Cart selectByProductIdAndUserId(String productId, String userId) {
		// TODO Auto-generated method stub
		QueryWrapper<Cart> cartQueryWrapper = new QueryWrapper<>();
		cartQueryWrapper.eq("product_id",productId);
		cartQueryWrapper.eq("user_id",userId);
		
		return cartMapper.selectOne(cartQueryWrapper);
	}


	@Override
	public List<Cart> cart(String userId) {
		QueryWrapper<Cart> cartQueryWrapper = new QueryWrapper<>();
		cartQueryWrapper.eq("user_id",userId);
		List<Cart> cartList = cartMapper.selectList(cartQueryWrapper);
		
		return cartList;
	}


	@Override
	public void cartSave(Cart cart) {
		// TODO Auto-generated method stub
		cartMapper.insert(cart);
	}


	@Override
	public void cartSave(String productId, String userId) {
		// TODO Auto-generated method stub
		// 查询产品信息
		Product product = productService.selectProdById(productId);
		if(product==null) {
			System.out.println("无商品信息");
		}else {
			System.out.println(product);
		}
		

		// 查询cart的数量确认cart的id
		
		/*
		 * QueryWrapper<Cart> cartQueryWrapper = new QueryWrapper<>();
		 * cartQueryWrapper.select("  IFNULL( max(id),0) as maxId"); Map<String,
		 * Integer> map = getMap(cartQueryWrapper); System.out.println("id号：");
		 * System.out.println( cartMapper.selectOne(cartQueryWrapper) );
		 */
		QueryWrapper<Cart> cartQueryWrapper = new QueryWrapper<>();
		cartQueryWrapper.orderByDesc("id");
		cartQueryWrapper.last("limit 1");
		
		Integer cartId;
		Cart cartTemp = cartMapper.selectOne(cartQueryWrapper);
		if(cartTemp==null) {
			cartId = 1;
		}else {
			cartId = cartTemp.getId()+1;
		}
		
		//System.out.println("id号: ");System.out.println(cartId);
		
		Cart cart = new Cart(cartId,userId,productId,product.getProductImgurl(), product.getProductName() , product.getProductPrice(),1);
		this.cartSave(cart);
	}


	@Override
	public void deleteCart(String productId, String userId) {
		// TODO Auto-generated method stub
		QueryWrapper<Cart> cartQueryWrapper = new QueryWrapper<>();
		cartQueryWrapper.eq("product_id",productId);
		cartQueryWrapper.eq("user_id", userId);
		cartMapper.delete(cartQueryWrapper);
	}


	@Override
	public void updateCart(String productId, String userId, Integer num) {
		// TODO Auto-generated method stub
		QueryWrapper<Cart> cartQueryWrapper = new QueryWrapper<>();
		cartQueryWrapper.eq("product_id",productId);
		cartQueryWrapper.eq("user_id", userId);
		Cart cart = cartMapper.selectOne(cartQueryWrapper);
		cart.setNum(num);
		cartMapper.update(cart, cartQueryWrapper);
		
	}


	// 查看购物车内容
	@Override
	public List<Cart> query(String userId) {
		
		return null;
	}




	

}
