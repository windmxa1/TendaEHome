package org.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bean.CartBean;
import org.model.OrdersDetail;
import org.util.JsonUtils;
import org.util.RedisUtil;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test06 {
	static ObjectMapper mapper = JsonUtils.getMapperInstance();

	public static void main(String[] args) throws JsonParseException,
			JsonMappingException, IOException {
		 empty();
		// delete();
//		test();
	}

	private static void test() {
		System.out.println(RedisUtil.getData("1123"));
		RedisUtil.addData("1123", "sdfs", null);
		System.out.println(RedisUtil.getData("1123"));
		RedisUtil.del("1123");
		System.out.println(RedisUtil.getData("1123"));
		RedisUtil.addData("151-asd-51", "12651", null);
		RedisUtil.addData("151-asd", "12651", null);
		RedisUtil.addData("asd-51", "12651", null);
		RedisUtil.delAll("*asd");
		System.out.println(RedisUtil.getData("151-asd-51"));
		System.out.println(RedisUtil.getData("151-asd"));
		System.out.println(RedisUtil.getData("asd-51"));
		
	}

	private static void empty() throws JsonProcessingException {
		RedisUtil.addData("cart-" + 233,
				mapper.writeValueAsString(new ArrayList<>()), null);
	}

	private static void delete() throws JsonParseException,
			JsonMappingException, IOException {
		Integer actId = 1;
		String cartListStr = RedisUtil.getData("cart-" + 233);

		List<CartBean> cartList;
		if (cartListStr != null && !cartListStr.equals("")) {// 购物车存在
			JavaType javaType = JsonUtils.getCollectionType(ArrayList.class,
					CartBean.class);
			cartList = mapper.readValue(cartListStr, javaType);
			Iterator<CartBean> iterator = cartList.iterator();
			while (iterator.hasNext()) {
				CartBean cartBean = iterator.next();
				if (cartBean.getActId().equals(actId)) {// 活动存在
					iterator.remove();
				}
			}
			RedisUtil.addData("cart-" + 233,
					mapper.writeValueAsString(cartList), null);
		}
	}
}