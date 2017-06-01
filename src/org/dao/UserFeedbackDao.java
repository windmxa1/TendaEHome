package org.dao;

import java.util.List;

import org.model.UserFeedback;
import org.view.VUserFeedbackId;

public interface UserFeedbackDao {
	/**
	 * 添加或修改用户反馈信息
	 */
	public Integer saveOrUpdate(UserFeedback userFeedback);

	/**
	 * 修改反馈信息的已读状态
	 */
	public boolean updateRead(Integer id);

	/**
	 * 删除用户反馈信息
	 */
	public boolean delete(Integer id);

	/**
	 * 获取反馈信息列表
	 */
	public List<VUserFeedbackId> getList(Integer read, Integer start,
			Integer limit);
}
