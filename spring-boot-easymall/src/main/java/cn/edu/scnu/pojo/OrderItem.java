package cn.edu.scnu.pojo;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@TableName(value = "t_order_item")
public class OrderItem {
	private Long id;
	private String orderId;
	private String productId;
	private Integer num;
	private String productImage;
	private String productName;
	private Double productPrice;
	
	
	
	public OrderItem(Long id, String orderId, String productId, Integer num, String productImage, String productName,
			Double productPrice) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.productId = productId;
		this.num = num;
		this.productImage = productImage;
		this.productName = productName;
		this.productPrice = productPrice;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
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
	
	/*
	 * id             BIGINT(20)    (NULL)           NO      PRI     (NULL)   AUTO_INCREMENT  SELECT,INSERT,UPDATE,REFERENCES         
order_id       CHAR(100)     utf8_general_ci  YES             (NULL)                   SELECT,INSERT,UPDATE,REFERENCES         
product_id     CHAR(36)      utf8_general_ci  YES             (NULL)                   SELECT,INSERT,UPDATE,REFERENCES         
num            INT(11)       (NULL)           YES             0                        SELECT,INSERT,UPDATE,REFERENCES         
product_image  VARCHAR(500)  utf8_general_ci  YES             (NULL)                   SELECT,INSERT,UPDATE,REFERENCES         
product_name   VARCHAR(100)  utf8_general_ci  YES             (NULL)                   SELECT,INSERT,UPDATE,REFERENCES         
product_price  DOUBLE        (NULL)           YES             (NULL)                   SELECT,INSERT,UPDATE,REFERENCES         

	 */
}
