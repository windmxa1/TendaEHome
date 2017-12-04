package org.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dao.StaffDao;
import org.dao.imp.StaffDaoImp;
import org.model.Staff;
import org.model.StaffPromotion;
import org.model.UserStaff;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.ResultUtils;
import org.view.VStaffPromotionId;

@Controller("/back/StaffController")
@RequestMapping("/back/staff")
public class StaffController {
	Map<String, Object> data;
	StaffDao sDao;

	/**
	 * 提交推广信息
	 */
	@RequestMapping("/addStaffPromotion")
	@ResponseBody
	public Object addStaffPromotion(HttpServletRequest request, String content)
			throws Exception {
		sDao = new StaffDaoImp();
		String[] s = null;
		if (content.contains(",")) {
			s = content.split(",");
		} else {
			s = content.split("，");
		}
		if (sDao.getStaffPromotion(s[0]) != null) {
			return ResultUtils.toJson(101, "地址重复", "");
		}
		if (sDao.saveOrUpdate2(new StaffPromotion(s[0], s[1])) > 0) {
			return ResultUtils.toJson(100, "提交成功", "");
		} else {
			return ResultUtils.toJson(101, "提交失败，请重试", "");
		}
	}

	/**
	 * 查看推广信息列表
	 */
	@RequestMapping("/getStaffPromotionList")
	@ResponseBody
	public Object getStaffPromotionList(HttpServletRequest request,
			Integer start, Integer limit) throws Exception {
		sDao = new StaffDaoImp();
		List<VStaffPromotionId> list = sDao.getStaffPromotionList(start, limit);
		if (list == null) {
			return ResultUtils.toJson(101, "后台错误，请重试", "");
		}
		data = new HashMap<>();
		data.put("total", sDao.getStaffPromotionCount());
		data.put("list", list);
		return ResultUtils.toJson(100, "", data);
	}

	/**
	 * 查看员工列表
	 */
	@RequestMapping("/getStaffList")
	@ResponseBody
	public Object getStaffList(HttpServletRequest request, Integer start,
			Integer limit) throws Exception {
		sDao = new StaffDaoImp();
		List<Staff> list = sDao.getStaffList(start, limit);
		if (list == null) {
			return ResultUtils.toJson(101, "后台错误，请重试", "");
		}
		data = new HashMap<>();
		data.put("total", sDao.getStaffPromotionCount());
		data.put("list", list);
		return ResultUtils.toJson(100, "", data);
	}

	/**
	 * 删除员工
	 */
	@RequestMapping("/deleteStaff")
	@ResponseBody
	public Object getStaffList(HttpServletRequest request, Integer id)
			throws Exception {
		sDao = new StaffDaoImp();
		if (sDao.deleteStaff(id)) {
			return ResultUtils.toJson(100, "", "");
		}
		return ResultUtils.toJson(101, "操作失败，请重试", "");
	}

	/**
	 * 修改员工信息
	 */
	@RequestMapping("/updateStaff")
	@ResponseBody
	public Object updateStaff(HttpServletRequest request,
			@RequestBody Staff staff) throws Exception {
		sDao = new StaffDaoImp();
		if (sDao.saveOrUpdate(staff) == 0) {
			return ResultUtils.toJson(100, "", "");
		}
		return ResultUtils.toJson(101, "操作失败，请重试", "");
	}

	/**
	 * 新增员工
	 */
	@RequestMapping("/addStaff")
	@ResponseBody
	public Object addStaff(HttpServletRequest request, Staff staff)
			throws Exception {
		sDao = new StaffDaoImp();
		if (sDao.saveOrUpdate(staff) > 0) {
			return ResultUtils.toJson(100, "", "");
		}
		return ResultUtils.toJson(101, "操作失败，请重试", "");
	}

}
