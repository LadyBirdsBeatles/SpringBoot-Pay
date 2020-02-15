package com.xiaochen.beatles.service.impl;

import com.xiaochen.beatles.mapper.UserMapper;
import com.xiaochen.beatles.pojo.User;
import com.xiaochen.beatles.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public void saveUser(User user) {
		userMapper.insert(user);
	}

	@Override
	public void updateUserById(User user) {
		userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public void deleteUserById(String userId) {
		userMapper.deleteByPrimaryKey(userId);
	}

	@Override
	public User getUserById(String userId) {
		
		return userMapper.selectByPrimaryKey(userId);
	}
	
	@Override
	public List<User> getUserList() {
		List<User> userList = userMapper.selectByExample();
		return userList;
	}

}
