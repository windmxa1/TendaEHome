package org.dao;

import java.util.List;

import org.model.AfterSale;

public interface AfterSaleDao {

	/**
	 * 添加售后记录
	 * @param afterSale
	 * @param urlList
	 * @return
	 */
	public boolean addAfterSale(AfterSale afterSale, List<String> urlList);
	/**
	 * 获取售后单
	 * @param orderNum
	 * @return
	 */
	public AfterSale getAfterSale(String orderNum);
	/**
	 * 获取售后单
	 */
	public AfterSale getAfterSale(Long orderId);
	/**
	 * 修改售后单状态和处理结果
	 */
	public boolean updateAfterSale(AfterSale afterSale);


}
