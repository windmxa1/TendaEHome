package org.dao;

import org.model.UserInfo;

public interface UserInfoDao {
	/**
	 * 维护用户信息
	 */
	public Long saveOrUpdate(UserInfo userInfo);

	/**
	 * 删除用户信息
	 */
	public boolean delete(Long id);
	
}
