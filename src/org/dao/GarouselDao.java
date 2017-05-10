package org.dao;

import java.util.List;

import org.model.Garousel;
import org.view.VGarouselId;

public interface GarouselDao {
	/**
	 * 获取轮播图列表,按目录分组
	 */
	public List<VGarouselId> getList();
	/**
	 * 维护轮播图
	 * @param garousel
	 * @return
	 */
	public Long saveOrUpdate(Garousel garousel);
	/**
	 * 删除
	 */
	public boolean delete(Long id);

}
