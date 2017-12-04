package org.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dao.AfterSaleDao;
import org.dao.OrdersDao;
import org.dao.RefundDao;
import org.dao.imp.AfterSaleDaoImp;
import org.dao.imp.OrdersDaoImp;
import org.dao.imp.RefundDaoImp;
import org.model.AfterSale;
import org.model.Refund;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.JsonUtils;
import org.util.ResultUtils;
import org.util.Utils;
import org.view.VOrdersId;

@Controller
@RequestMapping("/back/refund")
public class AfterSaleController {
	OrdersDao oDao;
	RefundDao rDao;
	AfterSaleDao asDao;
	Map<String, Object> data;

	/**
	 * 查看售后处理中的订单
	 */
	@RequestMapping("/getAfterSaleOrder")
	@ResponseBody
	public Object getAfterSaleOrder(HttpServletRequest request, Integer start,
			Integer limit,Integer type) {
		oDao = new OrdersDaoImp();
		List<VOrdersId> list = oDao.getAfterSaleOrder(null, start, limit,type);
		if (list == null) {
			return ResultUtils.toJson(101, "服务器繁忙，请重试", "");
		}
		data = new HashMap<String, Object>();
		data.put("list", list);
		data.put("total", oDao.getAfterSaleCount());
		return ResultUtils.toJson(100, "", data);
	}

	/**
	 * 查看订单的退款详情
	 */
	@RequestMapping("/getRefundById")
	@ResponseBody
	public Object getRefundById(HttpServletRequest request, String refundId) {
		rDao = new RefundDaoImp();
		Refund refund = rDao.getRefund(refundId);
		if (refund == null) {
			return ResultUtils.toJson(101, "服务器异常，请刷新后重试", "");
		}
		data = new HashMap<>();
		data.put("refund", refund);
		return ResultUtils.toJson(100, "", data);
	}

	@RequestMapping("/doRefund")
	@ResponseBody
	public Object doRefund(Long orderId, Integer payWay, String reason,
			Double totalFee) throws Exception {
		oDao = new OrdersDaoImp();
		Long time = System.currentTimeMillis() / 1000;
		String refundId = time + Utils.ran4();
		Refund refund = new Refund(refundId, orderId, totalFee, 1, reason, time);
		switch (oDao.updateRefundId(orderId, refund, 1)) {
		case 0:
			return ResultUtils.toJson(101, "该订单尚未付款，无法进行退款操作，请重试", "");
		case 1:
			return ResultUtils.toJson(100, "取消订单成功，您的退款将在1~5个工作日内返还", "");
		case -1:
			return ResultUtils.toJson(101, "系统繁忙，请稍后重试或咨询客服", "");
		}
		return ResultUtils.toJson(101, "生成退款单失败，请重试", "");
	}

	/**
	 * 获取订单对应的售后单
	 */
	@RequestMapping("/getAfterSaleByOrder")
	@ResponseBody
	public Object getAfterSaleByOrder(HttpServletRequest request, Long orderId) {
		asDao = new AfterSaleDaoImp();
		AfterSale afterSale = asDao.getAfterSale(orderId);
		if (afterSale == null) {
			return ResultUtils.toJson(101, "服务器异常，请刷新后重试", "");
		}
		data = new HashMap<>();
		data.put("afterSale", afterSale);
		return ResultUtils.toJson(100, "", data);
	}

	/**
	 * 修改售后单
	 */
	@RequestMapping("/updateAfterSale")
	@ResponseBody
	public Object updateAfterSale(AfterSale afterSale) throws Exception {
		System.out.println(JsonUtils.getMapperInstance().writeValueAsString(
				afterSale));
		asDao = new AfterSaleDaoImp();
		afterSale.setTime(System.currentTimeMillis() / 1000);
		if (asDao.updateAfterSale(afterSale)) {
			return ResultUtils.toJson(100, "修改成功", "");
		}
		return ResultUtils.toJson(101, "服务器异常，请刷新后重试", "");
	}
}
