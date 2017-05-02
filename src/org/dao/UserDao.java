package org.dao;

import org.model.User;
import org.view.VUserId;

public interface UserDao {
	/**
	 * 获取用户对象
	 */
	public VUserId getUser(String phone,String password);
	/**
	 * 增加用户
	 */
	public Long saveOrUpdate(User user);
	/**
	 * 删除用户
	 */
	public boolean delete(Long id);
	
	/**
	 * 修改密码
	 */
	public boolean updatePassword(String password, Long userid);
	/**
	 * 修改昵称
	 */
	public boolean updateNickname(String nickname, Long userid);
	/**
	 * 更新用户头像
	 */
	public boolean updateHead(String url, Long userid);
	/**
	 * 获取已用头像
	 */
	public String getUsedHead(Long userid);
}
