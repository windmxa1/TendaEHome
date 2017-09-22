package org.dao;

import java.util.List;

import org.model.Tv;
import org.view.VTvId;

public interface TvDao {
	/**
	 * 获取可见的直播列表
	 */
	public List<VTvId> getTVList(Integer start, Integer limit);

	/**
	 * 获取所有直播总数
	 */
	public Long getTVCount();

	/**
	 * 添加或修改直播
	 */
	public Integer saveOrUpdate(Tv tv);

	/**
	 * 删除直播
	 */
	public Boolean deleteTv(Integer id);

	/**
	 * 获取单个直播对象
	 */
	public Tv getTv(Integer id);
	/**
	 * 获取直播列表
	 */
	List<VTvId> getTVList1(Integer start, Integer limit);
	/**
	 * 修改可见状态
	 */
	Boolean updateTvAvailalbe(Integer id, Integer available);
}
