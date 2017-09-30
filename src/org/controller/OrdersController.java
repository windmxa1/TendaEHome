package org.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bean.WXNotify;
import org.bean.WXRETURN;
import org.dao.OrdersDao;
import org.dao.imp.OrdersDaoImp;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.Constants;
import org.util.JsonUtils;
import org.util.PDFUtil;
import org.util.ResultUtils;
import org.util.Utils;
import org.util.WXAPI;
import org.util.XmlUtils;
import org.view.VOrdersDetailsId;
import org.view.VOrdersId;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/back/orders")
public class OrdersController {
	OrdersDao oDao;
	// Long userid;
	Map<String, Object> data;

	@RequestMapping("/getOrdersList")
	@ResponseBody
	public Object getOrdersList(HttpServletRequest request, Integer start,
			Integer limit, Integer state) throws Exception {
		oDao = new OrdersDaoImp();
		data = new HashMap<String, Object>();
		List<VOrdersId> list = oDao.getListByState(start, limit, state);
		if (list == null || list.size() == 0) {
			data.put("list", new ArrayList<>());
		} else {
			data.put("list", list);
			data.put("total", oDao.getCountByState(state));
		}
		return ResultUtils.toJson(100, "", data);
	}

	@RequestMapping("/getOrdersPDF")
	@ResponseBody
	public Object getOrdersPDF(HttpServletRequest request, Integer state,
			String address) throws Exception {
		oDao = new OrdersDaoImp();
		List<VOrdersId> list = oDao.getListByState2(0, -1, state, address);
		for (VOrdersId v : list) {
			v.setDetails(oDao.getDetailList(v.getId(), 0, -1));
		}
		if (list == null || list.size() == 0) {
			return ResultUtils.toJson(100, "暂无订单", "");
		} else {
			String url = PDFUtil.buidPDF(Constants.watermark, list, 0);
			data = new HashMap<String, Object>();
			data.put("pdfUrl", url);
			return ResultUtils.toJson(100, "", data);
		}
	}

	@RequestMapping("/getOrdersDetails")
	@ResponseBody
	public Object getOrdersDetails(Long orderId, Integer start, Integer limit)
			throws Exception {
		oDao = new OrdersDaoImp();
		data = new HashMap<>();
		List<VOrdersDetailsId> list = oDao.getDetailList(orderId, start, limit);
		if (list == null || list.size() == 0) {
			data.put("list", new ArrayList<>());
		} else {
			data.put("list", list);
			data.put("total", oDao.getDetailsCount(orderId));
		}
		return ResultUtils.toJson(100, "", data);
	}

	@RequestMapping("/updateOrder")
	@ResponseBody
	public Object updateOrder(HttpServletRequest request, Long id, Integer state)
			throws Exception {
		oDao = new OrdersDaoImp();
		if (oDao.updateOrder(id, state)) {
			return ResultUtils.toJson(100, "修改成功", "");
		} else {
			return ResultUtils.toJson(101, "修改失败", "");
		}
	}

	@RequestMapping("/deleteOrder")
	@ResponseBody
	public Object deleteOrder(Long id) throws Exception {
		oDao = new OrdersDaoImp();
		if (oDao.deleteOrder(id) > 0) {
			return ResultUtils.toJson(100, "", "");
		}
		return ResultUtils.toJson(101, "不能删除正在进行的订单，如需请联系后台人员", "");
	}

	@RequestMapping("/getOrderByOrderNum")
	@ResponseBody
	public Object getOrderByOrderNum(String orderNum, Integer start,
			Integer limit) throws Exception {
		oDao = new OrdersDaoImp();
		data = new HashMap<>();
		VOrdersId v = oDao.getOrder(orderNum);
		if (v != null) {
			List<VOrdersDetailsId> details = oDao.getDetailList(v.getId(),
					start, limit);
			v.setDetails(details);
			data.put("list", v);
			data.put("total", 1);
		} else {
			data.put("list", new ArrayList<>());
		}
		return ResultUtils.toJson(100, "", data);
	}

	@RequestMapping("/staffDeliverOrder")
	@ResponseBody
	public Object staffDeliverOrder(String staffId, String orderNum)
			throws Exception {
		oDao = new OrdersDaoImp();
		if (oDao.updateOrdersStaffId(staffId, orderNum)) {
			return ResultUtils.toJson(100, "绑定成功", "");
		} else {
			return ResultUtils.toJson(101, "绑定失败，请重试", "");
		}
	}

	@RequestMapping("/notifyWxPay")
	@ResponseBody
	public Object notifyWxPay(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		oDao = new OrdersDaoImp();
		String result;
		String inputLine;
		String notityXml = "";
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		// 微信给返回的东西
		try {
			while ((inputLine = request.getReader().readLine()) != null) {
				notityXml += inputLine;
			}
			request.getReader().close();
		} catch (Exception e) {
			e.printStackTrace();
			result = WXAPI.setXml("FAIL", "xml获取失败");
			return result;
		}
		if (StringUtils.isEmpty(notityXml)) {
			result = WXAPI.setXml("FAIL", "xml为空");
			System.out.println(result);
			return result;
		}
		try {
			System.out.println(notityXml);
			Map map = XmlUtils.xml2map(notityXml, false);
			if (map.get("return_code").equals("SUCCESS")) {// 通信成功
				if (map.get("result_code").equals("SUCCESS")) {// 交易成功
					if (WXAPI.validSign(map)
							&& oDao.updateOrder("" + map.get("out_trade_no"), 2)) {// 验签成功且修改成功返回SUCCESS
						return WXAPI.setXml("SUCCESS", "OK");
					}
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return WXAPI.setXml("FAIL", "校验失败，请重试");
	}

}
