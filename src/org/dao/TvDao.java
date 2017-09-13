package org.dao;

import java.util.List;

import org.model.Tv;
import org.view.VTvId;

public interface TvDao {
	/**
	 * 获取直播列表
	 */
	public List<VTvId> getTVList(Integer start,Integer limit);
}
