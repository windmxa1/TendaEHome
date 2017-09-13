package org.test;

import java.util.ArrayList;
import java.util.List;

import org.dao.GoodsDao;
import org.dao.OrdersDao;
import org.dao.imp.GoodsDaoImp;
import org.dao.imp.OrdersDaoImp;
import org.util.Constants;
import org.util.JsonUtils;
import org.util.PDFUtil;
import org.view.VGoods;
import org.view.VOrdersId;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test05 {
	public static void main(String[] args) {
		// Long time = System.currentTimeMillis();
		OrdersDao oDao = new OrdersDaoImp();
		List<VOrdersId> list = oDao.getListByState(0, -1, 1);
		for (VOrdersId v : list) {
			v.setDetails(oDao.getDetailList(v.getId(), 0, -1));
		}
		PDFUtil.buidPDF(Constants.watermark, list, 0);

	}
}
