package cn.edu.scnu.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import cn.edu.scnu.pojo.Echarts;
import cn.edu.scnu.pojo.Product;
import cn.edu.scnu.vo.EasyUIResult;

public interface ProductService {

	public EasyUIResult allProductQuery();

	public Product selectProdById(String productId);

	public void productSave(Product product);

	public void productUpdate(Product product);

	public void productDelete(String[] ids);

	public void updateStatus(String[] ids, String status);

	public EasyUIResult allProductQuery2();

	public void exportExcel(String[] ids, HttpServletResponse response);

	public void exportExcel1(String[] ids, HttpServletResponse response);// 打印销售榜单

	public EasyUIResult allProductQuery3();

	// ld写
	public List<Product> searchsQuery(String query);

	// ld写，商品分类查找
	public EasyUIResult categoryProductQuery(String category);

	public List<Echarts> echartsShow();

}
