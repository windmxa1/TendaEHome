package org.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dao.AfterSaleDao;
import org.dao.FranchiseeDao;
import org.dao.GoodsDao;
import org.dao.OrdersDao;
import org.dao.RefundDao;
import org.dao.imp.AfterSaleDaoImp;
import org.dao.imp.FranchiseeDaoImp;
import org.dao.imp.GoodsDaoImp;
import org.dao.imp.OrdersDaoImp;
import org.dao.imp.RefundDaoImp;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.Orders;
import org.model.Refund;
import org.util.AESUtil;
import org.util.Constants;
import org.util.Coordinate;
import org.util.HibernateSessionFactory;
import org.util.JsonUtils;
import org.util.MD5;
import org.util.RedisUtil;
import org.util.ResultUtils;
import org.util.WXAPI;
import org.util.XmlUtils;
import org.view.VGoodsId;
import org.view.VOrders;
import org.view.VOrdersId;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

public class Test04 {
	public static void main(String[] args) throws JsonProcessingException {
		FranchiseeDao fDao = new FranchiseeDaoImp();
		List<Object[]> list = fDao.getLatLonList();
		for (Object[] o : list) {
			Coordinate coordinate = new Coordinate(
					Double.parseDouble(o[0] + ""),
					Double.parseDouble(o[1] + ""), "" + o[2]);
			System.out.println(JsonUtils.getMapperInstance()
					.writeValueAsString(coordinate));
			// RedisUtil.addReo(coordinate);
		}
	}
}
