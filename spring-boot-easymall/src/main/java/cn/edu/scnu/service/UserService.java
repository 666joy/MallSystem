package cn.edu.scnu.service;

import java.util.List;

import cn.edu.scnu.pojo.User;

public interface UserService {
	public User selectByUsername(String userName);
	public User queryUserJson(String ticket);
	public void userSave(User user);
	public List<User> search();
}
