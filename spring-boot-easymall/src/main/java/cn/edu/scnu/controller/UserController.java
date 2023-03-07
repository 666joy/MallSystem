package cn.edu.scnu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.edu.scnu.utils.MapperUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import cn.edu.scnu.pojo.OrderAndOrderItems;
import cn.edu.scnu.pojo.User;
import cn.edu.scnu.service.UserService;
import cn.edu.scnu.utils.MD5Util;
import cn.edu.scnu.utils.MapperUtil;
import cn.edu.scnu.utils.CookieUtils;
import cn.edu.scnu.vo.SysResult;

@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("/user/login")
	public SysResult login(String userName, String userPassword, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		User user = userService.selectByUsername(userName);
		if (user == null) {
			return SysResult.build(201, "您输入的用户名错误", null);
		} else if (user.getUserPassword().equals(MD5Util.md5(userPassword))) {
			session.setAttribute("user", user);
			CookieUtils.setCookie(request, response, "EM_TICKET", user.getUserId());
			return SysResult.ok();
		} else {
			return SysResult.build(201, "您输入的密码错误", null);
		}
	}
	@RequestMapping("/user/managelogin")
	public SysResult managelogin(String userName, String userPassword, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		User user = userService.selectByUsername(userName);
		if (user == null) {
			return SysResult.build(201, "您输入的用户名错误", null);
		} else if (user.getUserPassword().equals(MD5Util.md5(userPassword))) {
			if(user.getUserType()==0)
			{
				return SysResult.build(201, "该用户为普通用户，权限不足", 0);
			}else{
				session.setAttribute("user", user);
				CookieUtils.setCookie(request, response, "EM_TICKET", user.getUserId());
				return SysResult.build(200, "manage", user.getUserType());
			}
		} else {
			return SysResult.build(201, "您输入的密码错误", null);
		}
	}
	
	@RequestMapping("/user/query/{ticket}")
	public SysResult checkLoginUser(@PathVariable String ticket) {
		User user = userService.queryUserJson(ticket);
		String userJson = "";
		try {
			userJson = MapperUtil.MP.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		// 判断非空
		if (userJson != null) {
			// 确实曾经登录过.也正在登录使用状态中
			return SysResult.build(200, "ok", userJson);
		} else {
			return SysResult.build(201, "查不到", null);
		}
	}
	
	@RequestMapping("/user/manage_auto_login")
	public SysResult manage_auto_login(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		User user=(User) session.getAttribute("user");
		if(user.getUserType()==1||user.getUserType()==2){
			return SysResult.ok();
		}
		else{
			return SysResult.build(201, "该用户权限不足，请重新登录", null);
		}
	}

	@RequestMapping("/user/logout")
	public SysResult logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		session.setAttribute("user", null);
		CookieUtils.deleteCookie(request, response, "EM_TICKET");
		return SysResult.ok();
	}

	@RequestMapping("/user/checkUserName")
	public SysResult checkUsername(String userName) {
		User user = userService.selectByUsername(userName);
		if (user == null) {
			return SysResult.ok();
		}
		return SysResult.build(201, "用户名已存在", null);
	}

	@RequestMapping("/user/save")
	public SysResult userSave(User user) {
		// 1.username是否已被注册
		User user1 = userService.selectByUsername(user.getUserName());
		if (user1 != null) {
			return SysResult.build(201, "用户名已存在", null);
		}
		try {
			userService.userSave(user);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, "添加失败", null);
		}
	}
	
	@RequestMapping("/user/search")
	public List<User> search(){
		return userService.search();
	}
}