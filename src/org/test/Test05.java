package org.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.util.RedisUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.sortedset.ZAddParams;

public class Test05 {

	public static void main(String[] args) {
		Jedis jedis = null;
		try {
			jedis = RedisUtil.getJedis();
			String keys = "cart_goods-" + 233;
			/** list **/
			// jedis.lpush("cart_goods-" + 233, "13-233");
			// jedis.lpush("cart_goods-" + 233, "14-251");
			// jedis.lpush("cart_goods-" + 233, "01-231");
			// jedis.lpush("cart_goods-" + 233, "06-165");
			// jedis.lpush("cart_goods-" + 233, "05-241");
			// jedis.lpush("cart_goods-" + 233, "02-351");
			// System.out.println(jedis.lrange("cart_goods-" + 233, 0, -1));
			// jedis.lrem("cart_goods-" + 233, -1, "05-241");
			// System.out.println(jedis.lrange("cart_goods-" + 233, 0, -1));
			// jedis.del("cart_goods-" + 233);
			/** set **/
			// jedis.sadd("cart_goods-" + 233, "13-233-1");
			// jedis.sadd("cart_goods-" + 233, "14-251-2");
			// jedis.sadd("cart_goods-" + 233, "01-231-3");
			// jedis.sadd("cart_goods-" + 233, "06-165-4");
			// jedis.sadd("cart_goods-" + 233, "05-241-5");
			// jedis.sadd("cart_goods-" + 233, "02-351-6");
			// System.out.println(jedis.smembers("cart_goods-" + 233));
			// System.err.println(jedis.sinter(keys));
			// Set<String> set = jedis.sinter(keys);
			// set.remove("01-231-3");
			// set.remove("02-351-6");
			// set.add("05-124-7");
			// System.out.println(set.retainAll(jedis.sinter(keys)));
			// System.out.println(set.size());
			// set.containsAll(jedis.sinter(keys));
			// // jedis.lrem("cart_goods-" + 233, -1, "05-241");
			// jedis.srem("cart_goods-" + 233, "05-241");
			// System.out.println(jedis.smembers("cart_goods-" + 233));
			/** zset **/
			// jedis.zadd("cartgoods-" + 233, 13, "13-233-1");
			// jedis.zadd("cartgoods-" + 233, 14, "14-251-2");
			// jedis.zadd("cartgoods-" + 233, 1, "1-231-3");
			// jedis.zadd("cartgoods-" + 233, 0, "0-231-3");
			// jedis.zadd("cartgoods-" + 233, 06, "6-165-4");
			// jedis.zadd("cartgoods-" + 233, 5, "5-241-5");
			// jedis.zadd("cartgoods-" + 233, 2, "2-351-6");
			// jedis.zadd("cartgoods-" + 233, 0, "0-351-6");
			// System.out.println(jedis.zrange("cartgoods-" + 233, 0, -1));
			/** list **/
			jedis.flushAll();
			Long userid = 233L;
			String key1 = userid + "-actList";
			jedis.lpush(key1, "0");
			jedis.lpush(key1, "1");
			jedis.lpush(key1, "2");
			jedis.lpush(key1, "4");
			jedis.lpush(key1, "5");
			jedis.lpush(key1, "6");
			System.out.println(jedis.lrange(key1, 0, -1));
			String key2 = userid + "-actList-0";
			jedis.hset(key2, "12", "1");
			jedis.hset(key2, "13", "14");
			jedis.hset(key2, "1", "3");
			jedis.hset(key2, "3", "7");
			jedis.hset(key2, "6", "8");
			jedis.hincrBy(key2, "13", 1);
			jedis.hset(key2, "6", "16");
			jedis.hdel(key2, "3");
			Map<String, String> map = new HashMap<String, String>();
			map.put("123", "345");
			jedis.hmset(key2, map);
			System.out.println(jedis.hgetAll(key2));

			Set<String> set = jedis.keys(userid + "*");
			for (String str : set) {
				jedis.del(str);
			}
			System.out.println(jedis.hgetAll(key2));
			System.out.println(jedis.lrange(key1, 0, -1));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
