package org.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dao.OrdersDao;
import org.dao.imp.OrdersDaoImp;
import org.model.Orders;
import org.model.OrdersDetail;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.ResultUtils;
import org.util.TokenUtils;
import org.view.VOrdersDetailsId;
import org.view.VOrdersId;

@Controller
@RequestMapping("/back/orders")
public class OrdersController {
	OrdersDao oDao;
	// Long userid;
	Map<String, Object> data;
	
	
	@RequestMapping("/getOrdersList")
	@ResponseBody
	public Object getOrdersList(HttpServletRequest request, Integer start,
			Integer limit,Integer state) {
		oDao = new OrdersDaoImp();
		List<VOrdersId> list = oDao.getListByState(start, limit,state);
		if (list == null || list.size() == 0) {
			data.put("list", new ArrayList<>());
		} else {
			for (VOrdersId order : list) {
				List<VOrdersDetailsId> details = oDao.getDetailList(
						order.getId(), start, limit);
				order.setList(details);
			}
			data.put("list", list);
		}
		return ResultUtils.toJson(100, "", data);
	}
	
	@RequestMapping("updateOrder")
	@ResponseBody
	public Object updateOrder(HttpServletRequest request,Long id,Integer state){
		oDao = new OrdersDaoImp();
		if(oDao.updateOrder(id, state)){
			return ResultUtils.toJson(100, "", data);
		}else {
			return ResultUtils.toJson(101, "修改失败", data);
		}
	}
	
}
