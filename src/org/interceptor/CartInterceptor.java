package org.interceptor;

import java.security.Key;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bean.CartBean;
import org.dao.ActivityDao;
import org.dao.GoodsDao;
import org.dao.imp.ActivityDaoImp;
import org.dao.imp.GoodsDaoImp;
import org.model.OrdersDetail;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.util.JsonUtils;
import org.util.RedisUtil;
import org.util.ResultUtils;
import org.util.TokenUtils;
import org.view.VActivityId;
import org.view.VGoodsId;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CartInterceptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object object) throws Exception {
		Key key = TokenUtils.getKey();
		String token = request.getHeader("token");
		if (TokenUtils.isValid(token, key)) {
			Long userid = Long
					.parseLong(""
							+ TokenUtils.getValue(token, TokenUtils.getKey(),
									"userid"));
			List<CartBean> cartList = new ArrayList<>();
			if (RedisUtil.getData("cart-" + userid) == null) {// 如果为0，说明数据库更新过商品信息或活动信息
				Iterator<String> iterator = RedisUtil.getList(
						userid + "-cartList").iterator();
				ActivityDao aDao = new ActivityDaoImp();
				GoodsDao gDao = new GoodsDaoImp();
				while (iterator.hasNext()) {
					String actId = iterator.next();
					VActivityId a = aDao.getById(Integer.parseInt(actId));
					if (a != null) {
						Map<String, String> map = RedisUtil.getHashMap(userid
								+ "-cartList-" + actId);
						CartBean cartBean = new CartBean();
						List<OrdersDetail> details = new ArrayList<>();
						for (Map.Entry<String, String> entry : map.entrySet()) {
							VGoodsId gd = gDao.getVGoods(Long.parseLong(entry
									.getKey()));
							if (gd != null) {
								OrdersDetail od2 = new OrdersDetail();
								od2.setName(gd.getName());
								od2.setActId(a.getId());
								od2.setGoodsId(gd.getGoodsId());
								od2.setActMinPrice(a.getMinPrice());
								od2.setActName(a.getTitle());
								od2.setGoodsUrl(gd.getGoodsUrl());
								od2.setPrice(gd.getPrice());
								od2.setTime(gd.getTime());
								od2.setIsSelect(true);
								od2.setIsShopSelect(true);
								details.add(od2);
							}
						}
						if (details.size() == 0) {
							iterator.remove();
							RedisUtil.popList(userid + "-cartList", actId);
						} else {
							cartBean.setActId(1);
							cartBean.setName(a.getTitle());
							cartBean.setMinPrice(a.getMinPrice());
							cartBean.setList(details);
							cartList.add(cartBean);
						}
					}
				}
				RedisUtil.addData("cart-" + userid, JsonUtils.getMapperInstance()
						.writeValueAsString(cartList), null);
			}
			return true;
		} else {
			Map<String, Object> result = ResultUtils.toJson(400,
					"您的账户信息已过期或已在其他客户端登录，请重新登录", "");
			ObjectMapper objectMapper = new ObjectMapper();
			String s = objectMapper.writeValueAsString(result);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(s);
			return false;
		}
	}
}
