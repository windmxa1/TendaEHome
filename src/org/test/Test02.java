package org.test;

import org.dao.AdminDao;
import org.dao.GarouselCatalogDao;
import org.dao.GarouselDao;
import org.dao.GoodsDao;
import org.dao.OrdersDao;
import org.dao.UserAddressDao;
import org.dao.UserDao;
import org.dao.imp.AdminDaoImp;
import org.dao.imp.GarouselCatalogDaoImp;
import org.dao.imp.GarouselDaoImp;
import org.dao.imp.GoodsDaoImp;
import org.dao.imp.OrdersDaoImp;
import org.dao.imp.UserAddressDaoImp;
import org.dao.imp.UserDaoImp;
import org.model.GarouselCatalog;
import org.util.JsonUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test02 {
	final static OrdersDao oDao = new OrdersDaoImp();
	final static AdminDao aDao = new AdminDaoImp();
	final static UserDao uDao = new UserDaoImp();
	final static UserAddressDao uaDao = new UserAddressDaoImp();
	final static GoodsDao gDao = new GoodsDaoImp();
	final static GarouselDao G_DAO = new GarouselDaoImp();
	final static GarouselCatalogDao GC_DAO = new GarouselCatalogDaoImp();
	final static ObjectMapper mapper = JsonUtils.getMapperInstance();
	
	public static void main(String[] args) throws JsonProcessingException, InterruptedException {
		GarouselCatalog gCatalog = new GarouselCatalog("asdas");
		gCatalog.setId(5);
//		GC_DAO.saveOrUpdate(gCatalog);
		Long time = System.currentTimeMillis();
		System.out.println(oDao.getOrder("0132"));
//		Thread.sleep(5*1000);
		System.out.println(System.currentTimeMillis()-time);
		time = System.currentTimeMillis();
		System.out.println(gDao.getGoods(3L));
//		System.out.println(oDao.getTotal("0132"));
		System.out.println(System.currentTimeMillis()-time);
		System.out.println(oDao.getCountByState(2));
	}
}
