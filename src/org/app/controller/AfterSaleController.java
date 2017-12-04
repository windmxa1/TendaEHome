package org.app.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dao.AfterSaleDao;
import org.dao.OrdersDao;
import org.dao.imp.AfterSaleDaoImp;
import org.dao.imp.OrdersDaoImp;
import org.model.AfterSale;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.util.ResultUtils;
import org.util.TokenUtils;
import org.util.Utils;
import org.view.VOrdersId;

@Controller("/app/RefundController")
@RequestMapping("/app/refund")
public class AfterSaleController {
	AfterSaleDao asDao;
	OrdersDao oDao;
	Map<String, Object> data;

	/**
	 * 提交售后申请
	 */
	@RequestMapping("/addAfterSale")
	@ResponseBody
	public Object addAfterSale(HttpServletRequest request,
			@RequestParam("file") CommonsMultipartFile[] files, Long orderId,
			String reason,Integer afterSaleState) {
		if(afterSaleState>0){
			return ResultUtils.toJson(101, "申请失败，您已申请过该订单的售后处理", ""); 
		}
		Long time = System.currentTimeMillis() / 1000;
		String afterSaleId1 = time + Utils.ran4();
		AfterSale afterSale = new AfterSale(afterSaleId1, orderId, reason, time);
		String path = request.getSession().getServletContext()
				.getRealPath("/upload/afterSale/");
		List<String> urlList = new ArrayList<>();
		try {
			for (CommonsMultipartFile file : files) {
				String filename = time + "_" + file.getOriginalFilename();
				String filePath = path + filename;
				File newFile = new File(filePath);
				// 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
				file.transferTo(newFile);
				urlList.add("/upload/afterSale/" + filename);
			}
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			return ResultUtils.toJson(101, "图片上传失败，请重试", "");
		}
		asDao = new AfterSaleDaoImp();
		// 执行批量插入图片操作以及增加售后记录
		if (asDao.addAfterSale(afterSale, urlList)) {
			return ResultUtils.toJson(100, "申请成功，请等待我们客服人员处理，如有疑问请电话咨询我们", "");
		}
		return ResultUtils.toJson(101, "申请失败，请重试", "");
	}

	/**
	 * 查看售后处理中的订单，同时附带售后状态
	 */
	@RequestMapping("/getAfterSaleOrder")
	@ResponseBody
	public Object getAfterSaleOrder(HttpServletRequest request, Integer start,
			Integer limit,Integer type) {
		oDao = new OrdersDaoImp();
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		/*********************************/
		List<VOrdersId> list = oDao.getAfterSaleOrder(userid, start, limit,type);
		if (list == null || list.size() == 0) {
			return ResultUtils.toJson(101, "您没有处理中的售后订单哦", "");
		}
		data = new HashMap<String, Object>();
		asDao = new AfterSaleDaoImp();
		for (VOrdersId order : list) {
			order.setAfterSale(asDao.getAfterSale(order.getId()));
			order.setUrlList(oDao.getUrlList(order.getId()));
		}
		data.put("list", list);
		return ResultUtils.toJson(100, "", data);
	}

}
