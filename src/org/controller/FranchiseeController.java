package org.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dao.FranchiseeDao;
import org.dao.imp.FranchiseeDaoImp;
import org.model.Franchisee;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.util.ResultUtils;

@RestController("/back/FranchiseeController")
@RequestMapping("/back/franchisee")
public class FranchiseeController {
	Map<String, Object> data;
	FranchiseeDao fDao;

	/**
	 * 获取加盟商(厨师)列表
	 */
	@RequestMapping("getFranchiseeList")
	public Object getFranchiseeByCatlog(HttpServletRequest request,
			Integer catalogId, Integer start, Integer limit) {
		fDao = new FranchiseeDaoImp();
		List<Franchisee> list = fDao.getList(start, limit, catalogId);
		if (list == null) {
			return ResultUtils.toJson(101, "服务器繁忙请重试", "");
		}
		data = new HashMap<String, Object>();
		data.put("list", list);
		return ResultUtils.toJson(100, "", data);
	}

	/**
	 * 修改加盟商信息
	 */
	@RequestMapping("updateFranchisee")
	public Object updateFranchisee(HttpServletRequest request,
			Franchisee franchisee) {
		fDao = new FranchiseeDaoImp();
		if (fDao.saveOrUpdate(franchisee) == 0) {
			return ResultUtils.toJson(100, "操作成功", "");
		}
		return ResultUtils.toJson(101, "操作失败", "");
	}

}
