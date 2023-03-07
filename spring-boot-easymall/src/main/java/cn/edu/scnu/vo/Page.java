package cn.edu.scnu.vo;

import java.util.List;

import cn.edu.scnu.pojo.Product;

/**
 * @author admin
 *
 */
public class Page {
	//总页数
	private Integer totalPage;
	//当前页数
	private Integer currentPage;
	//查询分页结果
	private List<Product> products;
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}
