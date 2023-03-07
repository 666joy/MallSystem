package cn.edu.scnu.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@TableName(value = "t_product")
public class Product {
	private static final long serialVersionUID = -5644799954031156649L;
	// 根据驼峰命名定义属性
	// value与数据库主键列名一致，若实体类属性名与表主键列名一致可省略value
	@TableId(value = "product_id")
	private String productId;
	private String productName;
	// 封装类完成基本类型的使用
	// 满足业务意义
	private Double productPrice;
	private String productCategory;
	private String productImgurl;
	private Integer productNum;
	private String productDescription;
	private String productStatus;
	private Integer productSellnum;

	public Product(String productId, String productName, Double productPrice, String productCategory,
			String productImgurl, Integer productNum, String productDescription, String productStatus,
			Integer productSellnum) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productCategory = productCategory;
		this.productImgurl = productImgurl;
		this.productNum = productNum;
		this.productDescription = productDescription;
		this.productStatus = productStatus;
		this.productSellnum = productSellnum;
	}

	public Product() {
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	// getter setter
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductImgurl() {
		return productImgurl;
	}

	public void setProductImgurl(String productImgurl) {
		this.productImgurl = productImgurl;
	}

	public Integer getProductNum() {
		return productNum;
	}

	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	
	public Integer getProductSellnum() {
		return productSellnum;
	}

	public void setProductSellnum(Integer productSellnum) {
		this.productSellnum = productSellnum;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", productCategory=" + productCategory + ", productImgurl=" + productImgurl + ", productNum="
				+ productNum + ", productDescription=" + productDescription + ", productStatus=" + productStatus
				+ ", productSellnum=" + productSellnum + "]";
	}

	

}
