package org.test;

import org.bean.OrderModel;
import org.model.User;
import org.util.ChangeTime;
import org.util.RedisUtil;
import org.util.Utils;

public class Test07 {
	public static void main(String[] args) {
		OrderModel o = new OrderModel();
		o.setTotal(40.0d);
		o.setType(0);
		User user = new User();
		user.setIsFree(1);
		Long userid = 41L;
		boolean isFree = false;
		Long time = System.currentTimeMillis();
		if (o.getType() == 0 && user.getIsFree() == 1 && o.getTotal() < 50d) {// 有免单特权且总价低于50
			Integer count = Utils
					.parseInt(RedisUtil.getData("userid" + userid));
			if (count == null) {// 当天未免单，则免单
				RedisUtil.addData("userid" + userid, "" + 1,
						ChangeTime.weekendTime(24, time));
				isFree = true;
			} else if (count < 3) { // 本周有下单且免单数小于3，则免单
				RedisUtil.addData("userid" + userid, "" + (++count),
						ChangeTime.weekendTime(24, time));
				isFree = true;
			}
		}
	}
}
