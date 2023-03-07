package cn.edu.scnu.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@TableName(value = "t_cart")
public class Cart implements Serializable {
	private Integer id;
	private String userId;
	private String productId;
	private String productImage;
	private String productName;
	private Double productPrice;
	private Integer num;
	
	
	// ld写，含参构造
	public Cart(Integer id, String userId, String productId, String productImage, String productName,
			Double productPrice, Integer num) {
		super();
		this.id = id;
		this.userId = userId;
		this.productId = productId;
		this.productImage = productImage;
		this.productName = productName;
		this.productPrice = productPrice;
		this.num = num;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
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
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "Cart [id=" + id + ", userId=" + userId + ", productId=" + productId + ", productImage=" + productImage
				+ ", productName=" + productName + ", productPrice=" + productPrice + ", num=" + num + "]";
	}
	
	
	
	/*
	 * id             INT(11)       (NULL)           NO      PRI     (NULL)   AUTO_INCREMENT  SELECT,INSERT,UPDATE,REFERENCES         
user_id        VARCHAR(100)  utf8_general_ci  YES             (NULL)                   SELECT,INSERT,UPDATE,REFERENCES         
product_id     VARCHAR(100)  utf8_general_ci  YES             (NULL)                   SELECT,INSERT,UPDATE,REFERENCES         
product_image  VARCHAR(500)  utf8_general_ci  YES             (NULL)                   SELECT,INSERT,UPDATE,REFERENCES         
product_name   VARCHAR(100)  utf8_general_ci  YES             (NULL)                   SELECT,INSERT,UPDATE,REFERENCES         
product_price  DOUBLE        (NULL)           YES             (NULL)                   SELECT,INSERT,UPDATE,REFERENCES         
num            INT(11)       (NULL)           YES             (NULL)                   SELECT,INSERT,UPDATE,REFERENCES         

	 */
}
