package org.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bean.CartBean;
import org.bean.CartResult;
import org.dao.AdminDao;
import org.dao.GarouselCatalogDao;
import org.dao.GarouselDao;
import org.dao.GoodsCatalogDao;
import org.dao.GoodsDao;
import org.dao.OrdersDao;
import org.dao.UserAddressDao;
import org.dao.UserDao;
import org.dao.imp.AdminDaoImp;
import org.dao.imp.GarouselCatalogDaoImp;
import org.dao.imp.GarouselDaoImp;
import org.dao.imp.GoodsCatalogDaoImp;
import org.dao.imp.GoodsDaoImp;
import org.dao.imp.OrdersDaoImp;
import org.dao.imp.UserAddressDaoImp;
import org.dao.imp.UserDaoImp;
import org.model.Orders;
import org.model.OrdersDetail;
import org.util.JsonUtils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test02 {
	final static OrdersDao oDao = new OrdersDaoImp();
	final static AdminDao aDao = new AdminDaoImp();
	final static UserDao uDao = new UserDaoImp();
	final static UserAddressDao uaDao = new UserAddressDaoImp();
	final static GoodsDao gDao = new GoodsDaoImp();
	final static GoodsCatalogDao gcDao = new GoodsCatalogDaoImp();
	final static GarouselDao G_DAO = new GarouselDaoImp();
	final static GarouselCatalogDao GC_DAO = new GarouselCatalogDaoImp();
	final static ObjectMapper mapper = JsonUtils.getMapperInstance();

	public static void main(String[] args) throws InterruptedException,
			IOException {
		JavaType javaType = JsonUtils.getCollectionType(ArrayList.class,
				CartBean.class);

		String s = "{\"data\":[{\"actId\":1,\"name\":\"满二十减一\",\"list\":[{\"id\":null,\"orderId\":null,\"goodsId\":1,\"num\":null,\"prices\":null,\"goodsUrl\":null,\"name\":null,\"price\":null,\"description\":null,\"isSelect\":false,\"time\":1517552882368,\"isGift\":null,\"actId\":null,\"actName\":null},{\"id\":null,\"orderId\":null,\"goodsId\":2,\"num\":null,\"prices\":null,\"goodsUrl\":null,\"name\":null,\"price\":null,\"description\":null,\"isSelect\":false,\"time\":1517552882368,\"isGift\":null,\"actId\":null,\"actName\":null}]},{\"actId\":2,\"name\":\"满三十减4\",\"list\":[{\"id\":null,\"orderId\":null,\"goodsId\":3,\"num\":null,\"prices\":null,\"goodsUrl\":null,\"name\":null,\"price\":null,\"description\":null,\"isSelect\":false,\"time\":1517552882368,\"isGift\":null,\"actId\":null,\"actName\":null},{\"id\":null,\"orderId\":null,\"goodsId\":4,\"num\":null,\"prices\":null,\"goodsUrl\":null,\"name\":null,\"price\":null,\"description\":null,\"isSelect\":false,\"time\":1517552882368,\"isGift\":null,\"actId\":null,\"actName\":null},{\"id\":null,\"orderId\":null,\"goodsId\":5,\"num\":null,\"prices\":null,\"goodsUrl\":null,\"name\":null,\"price\":null,\"description\":null,\"isSelect\":false,\"time\":1517552882368,\"isGift\":null,\"actId\":null,\"actName\":null}]},{\"actId\":3,\"name\":\"满六十赠送礼品\",\"list\":[{\"id\":null,\"orderId\":null,\"goodsId\":6,\"num\":null,\"prices\":null,\"goodsUrl\":null,\"name\":null,\"price\":null,\"description\":null,\"isSelect\":false,\"time\":1517552882368,\"isGift\":null,\"actId\":null,\"actName\":null}]}],\"code\":100,\"msg\":\"success\"}";
		CartResult cartResult = mapper.readValue(s, CartResult.class);
		System.out.println(1);
		// List<CartBean> list =mapper.readValue(s, javaType);
	}
}
