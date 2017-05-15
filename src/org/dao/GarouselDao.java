package org.dao;

import java.util.List;

import org.model.Garousel;
import org.view.VGarouselId;

public interface GarouselDao {
	/**
	 *	获取指定轮播图 
	 */
	public VGarouselId getGarousel(Long id);
	/**
	 * 获取轮播图列表,按目录分组
	 */
	public List<VGarouselId> getList(Integer start,Integer limit);
	/**
	 * 获取轮播图列表，按目录ID
	 */
	public List<VGarouselId> getListByCatalog(Integer catalogId);
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
