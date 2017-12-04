package org.dao;

import java.util.List;

import org.model.Franchisee;

public interface FranchiseeDao {
	/**
	 * 增加或修改
	 */
	Long saveOrUpdate(Franchisee franchisee);

	/**
	 * 获取加盟商昵称列表
	 */
	List<String> getNicknameList(Integer catalogId);

	/**
	 * 获取加盟商(厨师)列表
	 */
	List<Franchisee> getList(Integer catalogId);
	/**
	 * 根据Id列表获取加盟商(厨师)列表
	 */
	List<Franchisee> getList(List<Long> ids);

	/**
	 * 获取加盟商(厨师)的坐标以及编号列表
	 */
	List<Object[]> getLatLonList();

	/**
	 * 根据昵称和加盟商目录ID获取加盟商
	 */
	Franchisee getFranchisee(String nickName, Integer catalogId);
}
