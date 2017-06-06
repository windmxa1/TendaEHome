package org.dao;

import java.util.List;

import org.model.Admin;
import org.view.VAdminId;

public interface AdminDao {
	/**
	 * 获取单个管理员对象
	 * @param phone
	 * @param password
	 * @return
	 */
	public Admin getAdmin(String username);
	/**
	 * 获取单个管理员对象
	 * @param phone
	 * @param password
	 * @return
	 */
	public Admin getAdmin(Long adminId, String password);
	/**
	 * 获取单个管理员对象
	 * @param phone
	 * @param password
	 * @return
	 */
	public Admin getAdmin(String username, String password);
	/**
	 * 获取单个管理员对象
	 * @param id
	 * @return
	 */
	public Admin getAdmin(Long id);
	/**
	 * 获取管理员列表
	 */
	public List<VAdminId> getAdmins(Integer start , Integer limit);
	/**
	 * 添加或修改管理员
	 */
	public Long saveOrUpdate(Admin admin);
	/**
	 * 删除管理员
	 */
	public boolean delete(Long id);
	/**
	 * 修改密码
	 */
	public boolean updatePassword(String password, Long adminId);
}
