package cn.edu.scnu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@TableName(value = "t_category")
public class ywdCategory {
	
	//根据驼峰命名定义属性
	//value与数据库主键列名一致，若实体类属性名与表主键列名一致可省略value
    @TableId(value = "category_id",type = IdType.AUTO)
	private String  categoryId;
	private String  categoryName;
	//封装类完成基本类型的使用
	//满足业务意义
	//getter setter
	public String getcategoryId() {
		return categoryId;
	}
	public void setcategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getcategoryName() {
		return categoryName;
	}
	public void setcategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Override
	public String toString() {
		return "ywdCategory [categoryId=" + categoryId + ", categoryName=" + categoryName + "]";
	}
	
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public ywdCategory() {
		super();
	}
	public ywdCategory(String categoryId, String categoryName) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}
	
	
}
