package org.util;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;

import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.params.geo.GeoRadiusParam;

public class RedisUtil {
	private static JedisPool jedisPool = null;
	// Redis服务器IP
	private static String ADDR = "localhost";
	// Redis的端口号
	private static int PORT = 6379;
	  
	// 访问密码
	// private static String AUTH = "111111";

	/**
	 * 初始化Redis连接池
	 */
	static {
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			// 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
			config.setBlockWhenExhausted(true);
			// 设置的逐出策略类名, 默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)
			config.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");
			// 是否启用pool的jmx管理功能, 默认true
			config.setJmxEnabled(true);
			// 最大空闲连接数, 默认8个 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
			config.setMaxIdle(8);
			// 最大连接数, 默认8个
			config.setMaxTotal(200);
			// 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
			config.setMaxWaitMillis(1000 * 100);
			// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
			config.setTestOnBorrow(true);
			jedisPool = new JedisPool(config, ADDR, PORT, 3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取Jedis实例
	 * 
	 * @return
	 */
	public synchronized static Jedis getJedis() {
		try {
			if (jedisPool != null) {
				Jedis resource = jedisPool.getResource();
				return resource;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 释放jedis资源
	 * 
	 * @param jedis
	 */
	public static void close(final Jedis jedis) {
		if (jedis != null) {
			jedis.close();
		}
	}

	/**
	 * 根据键获取值
	 */
	public static String getData(String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.get(key);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (null != jedis)
				jedis.close();
		}
		return null;
	}

	/**
	 * 增加数目
	 */
	public static boolean increase(String tbName, String key, Long value) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.hincrBy(tbName, key, value);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (null != jedis)
				jedis.close();
		}
		return false;
	}

	/**
	 * 存hashmap表
	 */
	public static Map<String, String> getHashMap(String tbName) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.hgetAll(tbName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (null != jedis)
				jedis.close();
		}
		return null;
	}

	/**
	 * 删除Map中的数据
	 */
	public static boolean del(String tbName, String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.hdel(tbName, key);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (null != jedis)
				jedis.close();
		}
		return false;
	}

	/**
	 * 删除关键字对应的数据
	 */
	public static boolean del(String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.del(key);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (null != jedis)
				jedis.close();
		}
		return false;
	}

	/**
	 * 存hashmap表
	 */
	public static String getHashMapValue(String tbName, String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.hget(tbName, key);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (null != jedis)
				jedis.close();
		}
		return null;
	}

	/**
	 * 
	 */
	public static boolean setHashMap(String tbName, Map<String, String> map) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.hmset(tbName, map);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (null != jedis)
				jedis.close();
		}
		return false;
	}

	/**
	 * 删除所有相关缓存
	 */
	public static boolean delAll(String pattern) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			Set<String> set = jedis.keys(pattern);
			for (String str : set) {
				jedis.del(str);
			}
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (null != jedis)
				jedis.close();
		}
		return false;
	}

	/**
	 * 创建/修改map中的键值对
	 */
	public static boolean setHashMap(String tbName, String key, String value) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.hset(tbName, key, value);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (null != jedis)
				jedis.close();
		}
		return false;
	}

	/**
	 * 获取列表
	 */
	public static List<String> getList(String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.lrange(key, 0, -1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (null != jedis)
				jedis.close();
		}
		return null;
	}

	/**
	 * 删除数组元素
	 */
	public static boolean popList(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.lrem(key, -1, value);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (null != jedis)
				jedis.close();
		}
		return false;
	}

	/**
	 * 添加数组元素
	 */
	public static boolean pushList(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.lpush(key, value);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (null != jedis)
				jedis.close();
		}
		return false;
	}

	/**
	 * 添加键值对
	 */
	public static boolean addData(String key, String value, Long time) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.set(key, value);
			if (time != null) {
				jedis.expireAt(key, time);
			}
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (null != jedis)
				jedis.close();
		}
		return false;
	}

	/**
	 * 添加坐标 key 经度 维度 距离 return m 表示单位为米
	 */
	public static Long addReo(Coordinate coordinate, String tag) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			// 第一个参数可以理解为表名
			return jedis.geoadd(tag, coordinate.getLongitude(),
					coordinate.getLatitude(), coordinate.getKey());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (null != jedis)
				jedis.close();
		}
		return null;
	}

	/**
	 * 查询附近人
	 * 
	 * @param coordinate
	 *            经纬度
	 * @param tag
	 *            标签，相当于表名
	 * @param radius
	 *            范围(单位为千米)
	 */
	public static List<GeoRadiusResponse> geoQuery(Coordinate coordinate,
			String tag, Double radius) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			// 200F GeoUnit.KM表示km
			return jedis.georadius(tag, coordinate.getLongitude(),
					coordinate.getLatitude(), radius, GeoUnit.KM,
					GeoRadiusParam.geoRadiusParam().withDist());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (null != jedis)
				jedis.close();
		}
		return null;
	}

	/**
	 * 查询附近人 key 经度 维度 距离 return GeoRadiusResponse
	 */
	public static List<GeoRadiusResponse> geoQuery(Coordinate coordinate,
			String tag) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			// 200F GeoUnit.KM表示km
			return jedis.georadius(tag, coordinate.getLongitude(), coordinate
					.getLatitude(), 5F, GeoUnit.KM, GeoRadiusParam
					.geoRadiusParam().withDist());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (null != jedis)
				jedis.close();
		}
		return null;
	}

}