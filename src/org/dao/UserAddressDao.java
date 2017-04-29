package org.dao;

import java.util.List;

import org.model.UserAddress;

public interface UserAddressDao {
	/**
	 * 维护用户地址信息
	 */
	public Long saveOrUpdate(UserAddress userAddress);

	/**
	 * 删除用户地址
	 */
	public boolean delete(Long id);
	
	/**
	 * 查出用户对应的地址信息
	 */
	public List<UserAddress> getList(Long userid);
}
