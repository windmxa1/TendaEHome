package org.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dao.RepairDao;
import org.dao.imp.RepairDaoImp;
import org.model.RepairOrder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.Constants;
import org.util.PDFUtil;
import org.util.ResultUtils;
import org.view.VRepairOrderId;

@Controller("/back/RepairController")
@RequestMapping("/back/repair")
public class RepairController {
	Map<String, Object> data;
	RepairDao rDao;

	/**
	 * 查看所有报修单
	 */
	@RequestMapping("/getRepairOrderList")
	@ResponseBody
	public Object getRepairOrderList(HttpServletRequest request, Integer start,
			Integer limit, Integer type[]) throws Exception{
		rDao = new RepairDaoImp();
		List<VRepairOrderId> list = rDao.getList(start, limit, type);

		if (list == null) {
			return ResultUtils.toJson(101, "后台错误，请重试", "");
		}
		data = new HashMap<>();
		data.put("total", rDao.getRepairListCount(type));
		data.put("list", list);
		return ResultUtils.toJson(100, "", data);
	}

	/**
	 * 修改报修单状态
	 */
	@RequestMapping("/updateRepairOrder")
	@ResponseBody
	public Object updateRepairOrder(HttpServletRequest request,
			@RequestBody RepairOrder repair) throws Exception{
		rDao = new RepairDaoImp();
		if (rDao.saveOrUpdate(repair) == 0L) {
			return ResultUtils.toJson(100, "修改成功", "");
		}
		return ResultUtils.toJson(101, "修改失败", "");
	}

	/**
	 * 导出报修单
	 */
	@RequestMapping("/getRepairOrderPDF")
	@ResponseBody
	public Object getRepairOrderPDF(HttpServletRequest request, Integer start,
			Integer limit, Integer type[]) throws Exception{
		rDao = new RepairDaoImp();
		List<VRepairOrderId> list = rDao.getList(start, limit, type);
		if (list == null || list.size() == 0) {
			return ResultUtils.toJson(101, "暂无报修单或后台错误，请重试", "");
		}
		PDFUtil.buidPDF(Constants.watermark, list, 1);
		return ResultUtils.toJson(100, "", "");
	}

	/**
	 * 删除报修单
	 */
	@RequestMapping("/deleteRepairOrder")
	@ResponseBody
	public Object deleteRepairOrder(HttpServletRequest request, Long[] id)throws Exception {
		rDao = new RepairDaoImp();
		if(id==null||id.length==0){
			return ResultUtils.toJson(101, "参数错误", "");
		}
		if (rDao.deleteRepairOrder(id)) {
			return ResultUtils.toJson(100, "删除成功", "");
		}
		return ResultUtils.toJson(101, "删除失败", "");

	}

}
