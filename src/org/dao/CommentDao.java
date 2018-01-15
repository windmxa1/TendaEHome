package org.dao;

import java.util.List;

import org.model.Comment;

public interface CommentDao {
	/**
	 * 添加或修改评论
	 */
	public Long saveOrupdate(Comment comment);

	/**
	 * 添加评论及图片
	 */
	public boolean addComment(Comment comment, List<String> urlList);

	/**
	 * 删除评论
	 */
	public boolean delete(Long commentId);

	/**
	 * 获取评论列表
	 */
	public List getList(Integer start,Integer limit);

	/**
	 * 根据餐厅获取评论列表(type=0为全部，1为好评，2为中评，3为差评)
	 */
	public List getList(Integer catalogId,Integer type,Integer start,Integer limit);
	/**
	 * 根据餐厅获取评论总数
	 */
	public Long getCount(Integer catalogId,Integer type);

	/**
	 * 根据厨师获取评论列表
	 */
	public List getList(Long franchiseeId,Integer start,Integer limit);
}
