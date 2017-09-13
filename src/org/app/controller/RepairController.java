package org.app.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dao.RepairDao;
import org.dao.imp.RepairDaoImp;
import org.model.RepairComment;
import org.model.RepairOrder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.ResultUtils;
import org.util.TokenUtils;
import org.view.VRepairOrderId;

@Controller("/app/RepairController")
@RequestMapping("/app/repair")
public class RepairController {
	Map<String, Object> data;
	RepairDao rDao;

	/**
	 * 用户提交报修单
	 */
	@RequestMapping("/addRepairOrder")
	@ResponseBody
	public Object addRepairOrder(HttpServletRequest request,
			String appointmentTime, String address, String description,
			String phone) {
		rDao = new RepairDaoImp();

		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		/*********************************/
		RepairOrder repairOrder = new RepairOrder(appointmentTime, address,
				description, phone, userid);
		if (rDao.saveOrUpdate(repairOrder) > 0) {
			return ResultUtils.toJson(100, "提交成功，我们会尽快处理", "");
		}
		return ResultUtils.toJson(101, "提交失败，服务器繁忙，请重试", "");
	}

	/**
	 * 用户查看报修单
	 */
	@RequestMapping("/getRepairOrderList")
	@ResponseBody
	public Object getRepairOrderList(HttpServletRequest request, Integer start,
			Integer limit) {
		rDao = new RepairDaoImp();
		
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		/*********************************/
		
		List<VRepairOrderId> list = rDao.getList(start, limit, userid);
		if(list==null||list.size()==0){
			return ResultUtils.toJson(100, "您还没有提交过报修单", "");
		}
		return ResultUtils.toJson(100, "", list);
	}

	/**
	 * 用户提交报修人员评价
	 */
	@RequestMapping("/addRepairComment")
	@ResponseBody
	public Object addRepairComment(HttpServletRequest request, Integer point,
			String description, String suggestion, String staffId) {
		rDao = new RepairDaoImp();

		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		/*********************************/
		RepairComment repairComment = new RepairComment(point, description,
				suggestion, staffId, userid);
		if (rDao.saveOrUpdate(repairComment) > 0) {
			return ResultUtils.toJson(100, "感谢您的评价，我们将继续努力提供让您满意的服务", "");
		}
		return ResultUtils.toJson(101, "提交失败，服务器繁忙，请重试", "");
	}
}
