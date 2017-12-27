package org.dao;

import java.util.List;

import org.model.User;
import org.view.VUserId;

public interface UserDao {
	/**
	 * 获取用户对象
	 */
	public User getUser(String phone);
	public User getUser(Long userid);

	/**
	 * 获取用户对象
	 */
	public User getUser(String phone, String password);

	public VUserId getVUser(String phone, String password);

	public VUserId getVUser(Long id);

	/**
	 * 获取用户总数
	 */
	public Long getUserCount();

	/**
	 * 获取用户对象
	 */
	public User getUser(Long userid, String password);

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

	/**
	 * 获取用户列表
	 */
	public List<VUserId> getUsers(Integer start, Integer limit);

	/**
	 * 修改免单特权
	 */
	public boolean updateFree(Integer isFree, Long userid);

	/**
	 * 获取免单用户列表
	 */
	public List<VUserId> getFreeList(Integer start, Integer limit);

	/**
	 * 获取免单用户数
	 */
	public Long getFreeCount();

	/**
	 * 获取搜索用户列表
	 */
	public List<VUserId> getUserByPhone(String phone, Integer start,
			Integer limit);

	/**
	 * 获取搜索用户数
	 */
	public Long getCount(String phone);

}
