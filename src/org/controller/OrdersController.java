package org.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dao.OrdersDao;
import org.dao.RefundDao;
import org.dao.imp.OrdersDaoImp;
import org.dao.imp.RefundDaoImp;
import org.dom4j.DocumentException;
import org.model.Refund;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.AESUtil;
import org.util.ALIPAY;
import org.util.Constants;
import org.util.PDFUtil;
import org.util.ResultUtils;
import org.util.Utils;
import org.util.WXAPI;
import org.util.XmlUtils;
import org.view.VOrdersDetailsId;
import org.view.VOrdersId;

import com.alipay.api.internal.util.AlipaySignature;

@Controller
@RequestMapping("/back/orders")
public class OrdersController {
	OrdersDao oDao;
	RefundDao rDao;
	// Long userid;
	Map<String, Object> data;

	@RequestMapping("/getOrdersList")
	@ResponseBody
	public Object getOrdersList(HttpServletRequest request, Integer start,
			Integer limit, Integer state, Integer type) throws Exception {
		oDao = new OrdersDaoImp();
		data = new HashMap<String, Object>();
		List<VOrdersId> list = oDao.getListByState(start, limit, state, type);
		if (list == null || list.size() == 0) {
			data.put("list", new ArrayList<>());
		} else {
			data.put("list", list);
			data.put("total", oDao.getCountByState(state, type));
		}
		return ResultUtils.toJson(100, "", data);
	}

	@RequestMapping("/getOrdersPDF")
	@ResponseBody
	public Object getOrdersPDF(HttpServletRequest request, Integer state,
			String address, String origin) throws Exception {
		oDao = new OrdersDaoImp();
		List<VOrdersId> list = oDao.getListByState2(0, -1, state, address, 0);
		for (VOrdersId v : list) {
			v.setDetails(oDao.getDetailList(v.getId(), 0, -1, origin));
		}
		if (list == null || list.size() == 0) {
			return ResultUtils.toJson(101, "暂无订单", "");
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
		List<VOrdersDetailsId> list = oDao.getDetailList(orderId, start, limit,
				null);
		if (list == null || list.size() == 0) {
			data.put("list", new ArrayList<>());
		} else {
			data.put("list", list);
			data.put("total", oDao.getDetailsCount(orderId));
		}
		return ResultUtils.toJson(100, "", data);
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
					start, limit, null);
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
	public Object staffDeliverOrder(String staffNo, String orderNum)
			throws Exception {
		oDao = new OrdersDaoImp();
		if (oDao.updateOrdersStaffNo(staffNo, orderNum)) {
			return ResultUtils.toJson(100, "绑定成功", "");
		} else {
			return ResultUtils.toJson(101, "绑定失败，请重试", "");
		}
	}

	@RequestMapping("/refundOrder")
	@ResponseBody
	public Object refundOrder(Long id, Double total) throws Exception {
		oDao = new OrdersDaoImp();
		Long time = System.currentTimeMillis() / 1000;
		String refundId = time + Utils.ran4();
		Refund refund = new Refund(refundId, id, total, 1, "正常退款", time);
		switch (oDao.updateRefundId1(id, refund)) {
		case 0:
			return ResultUtils.toJson(101, "该订单尚未付款，无法进行退款操作，请重试", "");
		case 1:
			return ResultUtils.toJson(100, "取消订单成功，您的退款将在1~5个工作日内返还", "");
		case -1:
			return ResultUtils.toJson(101, "系统繁忙，请稍后重试或咨询客服", "");
		}
		return ResultUtils.toJson(101, "生成退款单失败，请重试", "");
	}

	@RequestMapping("/cancelOrder")
	@ResponseBody
	public Object cancelOrder(HttpServletRequest request, Long id)
			throws Exception {
		oDao = new OrdersDaoImp();
		if (oDao.cancel(null, id) > 0) {
			return ResultUtils.toJson(100, "取消订单成功", "");
		}
		return ResultUtils.toJson(101, "取消订单失败，您的订单已取消或系统繁忙，请稍后重试", "");
	}

	@RequestMapping("/finishRefund")
	@ResponseBody
	public Object finishRefund(HttpServletRequest request, String refundId)
			throws Exception {
		rDao = new RefundDaoImp();
		if (rDao.updateState(refundId, 2)) {
			return ResultUtils.toJson(100, "取消订单成功", "");
		}
		return ResultUtils.toJson(101, "取消订单失败，您的订单已取消或系统繁忙，请稍后重试", "");
	}

	@RequestMapping("/finishOrder")
	@ResponseBody
	public Object finishOrder(HttpServletRequest request, Long id)
			throws Exception {
		oDao = new OrdersDaoImp();
		if (oDao.updateDeliveryState(2, null, id)) {
			return ResultUtils.toJson(100, "订单完成！", "");
		}
		return ResultUtils.toJson(101, "服务器繁忙，请您重试", "");
	}

	@RequestMapping("/notifyWxRefund")
	@ResponseBody
	public Object notifyWxRefund(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		oDao = new OrdersDaoImp();
		rDao = new RefundDaoImp();
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
			return result;
		}
		System.out.println(notityXml);
		Map map = XmlUtils.xml2map(notityXml, false);
		if (map.get("return_code").equals("SUCCESS")) {// 通信成功
			if (map.get("appid").equals(Constants.appid)
					&& map.get("mch_id").equals(Constants.mch_id)) {
				String req_info = AESUtil.decryptData(map.get("req_info")
						.toString());
				try {
					Map map2 = XmlUtils.xml2map(req_info, false);
					Refund refund = rDao.getRefund(""
							+ map2.get("out_refund_no"));
					switch (map2.get("refund_status") + "") {
					case "SUCCESS":
						refund.setState(0);
						break;
					case "CHANGE":
						refund.setState(-1);
						break;
					case "REFUNDCLOSE":
						refund.setState(-2);
						break;
					}
					if (refund != null
							&& 100 * refund.getRefundFee() == Double
									.parseDouble(map2.get("refund_fee") + "")
							&& rDao.saveOrUpdate(refund)) {
						return WXAPI.setXml("SUCCESS", "OK");
					}
				} catch (Exception e) {
					System.out.println(req_info);
					e.printStackTrace();
				}
			}
		}
		return WXAPI.setXml("FAIL", "校验失败");
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
					if (WXAPI.validSign(map)) {
						Double total = oDao.getTotal(""
								+ map.get("out_trade_no")) * 100;
						if (total == Double.parseDouble(""
								+ (map.get("total_fee")))) {// 微信支付的总金额单位是分
							if (oDao.updateOrder("" + map.get("out_trade_no"),
									1)) {// 验签成功且修改成功返回SUCCESS
								return WXAPI.setXml("SUCCESS", "OK");
							}
						} else {
							System.out.println("订单总价错误,参数总价为："
									+ map.get("total_fee") + "," + "订单实际总价为："
									+ total);
						}
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

	@RequestMapping("/notifyAliPay")
	@ResponseBody
	public Object notifyAliPay(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		// 切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
		boolean flag = AlipaySignature.rsaCheckV1(params,
				ALIPAY.ALIPAY_PUBLIC_KEY, ALIPAY.CHARSET, "RSA2");
		if (flag) {
			if (ALIPAY.isValid(params)) {
				return "success";
			} else {
				return "failure";
			}
		}
		return "failure";
	}

}