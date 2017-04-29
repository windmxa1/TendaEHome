package org.dao;

import org.model.User;

public interface UserDao {
	/**
	 * 获取用户对象
	 */
	public User getUser(String phone,String password);
	/**
	 * 增加用户
	 */
	public Long saveOrUpdate(User user);
	/**
	 * 删除用户
	 */
	public boolean delete(Long id);
	
	
	
}
