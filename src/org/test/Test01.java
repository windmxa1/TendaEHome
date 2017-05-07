package org.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.dao.OrdersDao;
import org.dao.UserDao;
import org.dao.imp.OrdersDaoImp;
import org.dao.imp.UserDaoImp;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.Orders;
import org.model.OrdersDetail;
import org.model.User;
import org.util.HibernateSessionFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test01 {
	public final static ObjectMapper mapper = new ObjectMapper();

	public static void main(String[] args) {
		UserDao uDao = new UserDaoImp();
		OrdersDao oDao = new OrdersDaoImp();
		Orders order = new Orders(1L, System.currentTimeMillis() / 1000, 0, 1L);
		OrdersDetail detail1 = new OrdersDetail();
		detail1.setNum(1d);
		detail1.setPrices(1d);
		detail1.setGoodsId(1L);
		OrdersDetail detail2 = new OrdersDetail();
		detail2.setNum(1d);
		detail2.setPrices(1d);
		detail2.setGoodsId(2L);

		List<OrdersDetail> details = new ArrayList<OrdersDetail>();
		details.add(detail1);
		details.add(detail2);
		oDao.generateOrder(order, details);
		
		
		
		Long time = System.currentTimeMillis();
		try {
			String s = "[{\"goodsId\":1,\"goodsUrl\":\"http://192.168.1.150:8080/TendaEHome/upload/goods/cabbage.jpg\",\"name\":\"xxx1\",\"num\":12,\"prices\":57.6,\"price\":4.8,\"description\":\"xxxx\"},{\"goodsId\":2,\"goodsUrl\":\"http://192.168.1.150:8080/TendaEHome/upload/goods/cabbage.jpg\",\"name\":\"xxx1\",\"num\":12,\"prices\":57.6,\"price\":4.8,\"description\":\"xxxx\"},{\"goodsId\":2,\"goodsUrl\":\"http://192.168.1.150:8080/TendaEHome/upload/goods/cabbage.jpg\",\"name\":\"xxx1\",\"num\":12,\"prices\":57.6,\"price\":4.8,\"description\":\"xxxx\"},{\"goodsId\":2,\"goodsUrl\":\"http://192.168.1.150:8080/TendaEHome/upload/goods/cabbage.jpg\",\"name\":\"xxx1\",\"num\":12,\"prices\":57.6,\"price\":4.8,\"description\":\"xxxx\"},{\"goodsId\":2,\"goodsUrl\":\"http://192.168.1.150:8080/TendaEHome/upload/goods/cabbage.jpg\",\"name\":\"xxx1\",\"num\":12,\"prices\":57.6,\"price\":4.8,\"description\":\"xxxx\"},{\"goodsId\":2,\"goodsUrl\":\"http://192.168.1.150:8080/TendaEHome/upload/goods/cabbage.jpg\",\"name\":\"xxx1\",\"num\":12,\"prices\":57.6,\"price\":4.8,\"description\":\"xxxx\"},{\"goodsId\":2,\"goodsUrl\":\"http://192.168.1.150:8080/TendaEHome/upload/goods/cabbage.jpg\",\"name\":\"xxx1\",\"num\":12,\"prices\":57.6,\"price\":4.8,\"description\":\"xxxx\"},{\"goodsId\":2,\"goodsUrl\":\"http://192.168.1.150:8080/TendaEHome/upload/goods/cabbage.jpg\",\"name\":\"xxx1\",\"num\":12,\"prices\":57.6,\"price\":4.8,\"description\":\"xxxx\"},{\"goodsId\":2,\"goodsUrl\":\"http://192.168.1.150:8080/TendaEHome/upload/goods/cabbage.jpg\",\"name\":\"xxx1\",\"num\":12,\"prices\":57.6,\"price\":4.8,\"description\":\"xxxx\"},{\"goodsId\":2,\"goodsUrl\":\"http://192.168.1.150:8080/TendaEHome/upload/goods/cabbage.jpg\",\"name\":\"xxx1\",\"num\":12,\"prices\":57.6,\"price\":4.8,\"description\":\"xxxx\"},{\"goodsId\":2,\"goodsUrl\":\"http://192.168.1.150:8080/TendaEHome/upload/goods/cabbage.jpg\",\"name\":\"xxx1\",\"num\":12,\"prices\":57.6,\"price\":4.8,\"description\":\"xxxx\"},{\"goodsId\":2,\"goodsUrl\":\"http://192.168.1.150:8080/TendaEHome/upload/goods/cabbage.jpg\",\"name\":\"xxx1\",\"num\":12,\"prices\":57.6,\"price\":4.8,\"description\":\"xxxx\"},{\"goodsId\":2,\"goodsUrl\":\"http://192.168.1.150:8080/TendaEHome/upload/goods/cabbage.jpg\",\"name\":\"xxx1\",\"num\":12,\"prices\":57.6,\"price\":4.8,\"description\":\"xxxx\"},{\"goodsId\":2,\"goodsUrl\":\"http://192.168.1.150:8080/TendaEHome/upload/goods/cabbage.jpg\",\"name\":\"xxx1\",\"num\":12,\"prices\":57.6,\"price\":4.8,\"description\":\"xxxx\"},{\"goodsId\":2,\"goodsUrl\":\"http://192.168.1.150:8080/TendaEHome/upload/goods/cabbage.jpg\",\"name\":\"xxx1\",\"num\":12,\"prices\":57.6,\"price\":4.8,\"description\":\"xxxx\"},{\"goodsId\":2,\"goodsUrl\":\"http://192.168.1.150:8080/TendaEHome/upload/goods/cabbage.jpg\",\"name\":\"xxx1\",\"num\":12,\"prices\":57.6,\"price\":4.8,\"description\":\"xxxx\"},{\"goodsId\":2,\"goodsUrl\":\"http://192.168.1.150:8080/TendaEHome/upload/goods/cabbage.jpg\",\"name\":\"xxx1\",\"num\":12,\"prices\":57.6,\"price\":4.8,\"description\":\"xxxx\"},{\"goodsId\":2,\"goodsUrl\":\"http://192.168.1.150:8080/TendaEHome/upload/goods/cabbage.jpg\",\"name\":\"xxx1\",\"num\":12,\"prices\":57.6,\"price\":4.8,\"description\":\"xxxx\"},{\"goodsId\":2,\"goodsUrl\":\"http://192.168.1.150:8080/TendaEHome/upload/goods/cabbage.jpg\",\"name\":\"xxx1\",\"num\":12,\"prices\":57.6,\"price\":4.8,\"description\":\"xxxx\"},{\"goodsId\":2,\"goodsUrl\":\"http://192.168.1.150:8080/TendaEHome/upload/goods/cabbage.jpg\",\"name\":\"xxx1\",\"num\":12,\"prices\":57.6,\"price\":4.8,\"description\":\"xxxx\"},{\"goodsId\":2,\"goodsUrl\":\"http://192.168.1.150:8080/TendaEHome/upload/goods/cabbage.jpg\",\"name\":\"xxx1\",\"num\":12,\"prices\":57.6,\"price\":4.8,\"description\":\"xxxx\"},{\"goodsId\":2,\"goodsUrl\":\"http://192.168.1.150:8080/TendaEHome/upload/goods/cabbage.jpg\",\"name\":\"xxx1\",\"num\":12,\"prices\":57.6,\"price\":4.8,\"description\":\"xxxx\"},{\"goodsId\":2,\"goodsUrl\":\"http://192.168.1.150:8080/TendaEHome/upload/goods/cabbage.jpg\",\"name\":\"xxx1\",\"num\":12,\"prices\":57.6,\"price\":4.8,\"description\":\"xxxx\"},{\"goodsId\":2,\"goodsUrl\":\"http://192.168.1.150:8080/TendaEHome/upload/goods/cabbage.jpg\",\"name\":\"xxx1\",\"num\":12,\"prices\":57.6,\"price\":4.8,\"description\":\"xxxx\"},{\"goodsId\":2,\"goodsUrl\":\"http://192.168.1.150:8080/TendaEHome/upload/goods/cabbage.jpg\",\"name\":\"xxx1\",\"num\":12,\"prices\":57.6,\"price\":4.8,\"description\":\"xxxx\"},{\"goodsId\":2,\"goodsUrl\":\"http://192.168.1.150:8080/TendaEHome/upload/goods/cabbage.jpg\",\"name\":\"xxx1\",\"num\":12,\"prices\":57.6,\"price\":4.8,\"description\":\"xxxx\"}]";
			JavaType javaType = getCollectionType(ArrayList.class, OrdersDetail.class); 
			Orders orders = new Orders(1L, System.currentTimeMillis() / 1000,
					0, 1L);
			List<OrdersDetail> list = mapper.readValue(s, javaType);
			oDao.generateOrder(orders, list);
//			for (OrdersDetail od : list) {
//				System.out.println(od.getGoodsId());
//			}
			System.out.println(System.currentTimeMillis()-time);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static JavaType getCollectionType(Class<?> collectionClass,
			Class<?>... elementClasses) {
		return mapper.getTypeFactory().constructParametricType(collectionClass,
				elementClasses);
	}
}
