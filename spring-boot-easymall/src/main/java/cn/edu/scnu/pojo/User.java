package cn.edu.scnu.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@TableName(value = "t_user")
public class User {
	private static final long serialVersionUID = -5644799954031156649L;
	//value与数据库主键列名一致，若实体类属性名与表主键列名一致可省略value
    @TableId(value = "user_id")
	private String userId;
	private String userName;
	private String userPassword;
	private String userNickname;
	private String userEmail;
	private Integer userType;//默认都是0
	public User(String userId, String userName, String userPassword, String userNickname, String userEmail,
			Integer userType) {
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userNickname = userNickname;
		this.userEmail = userEmail;
		this.userType = userType;
	}
	public User(){}
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
}
