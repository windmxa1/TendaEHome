package org.util;

import java.util.List;

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

//	public static void main(String[] args) {
//		Jedis jedis = RedisUtil.getJedis();
//
//		// 添加经纬度
//		Coordinate coordinate = new Coordinate();
//		coordinate.setLatitude(31.244803); // 维度
//		coordinate.setLongitude(121.483671); // 经度
//		coordinate.setKey("1"); // 可以作为用户表的id
//
//		// 添加经纬度
//		Coordinate coordinate1 = new Coordinate();
//		coordinate1.setLatitude(31.245321); // 维度
//		coordinate1.setLongitude(121.485015); // 经度
//		coordinate1.setKey("2"); // 可以作为用户表的id
//
//		// 添加经纬度
//		Coordinate coordinate4 = new Coordinate();
//		coordinate4.setLatitude(31.245331); // 维度
//		coordinate4.setLongitude(121.485015); // 经度
//		coordinate4.setKey("2"); // 可以作为用户表的id
//
//		// 添加经纬度
//		Coordinate coordinate2 = new Coordinate();
//		coordinate2.setLatitude(31.245321); // 维度
//		coordinate2.setLongitude(121.485015); // 经度
//		// coordinate2.setLatitude(31.245456); // 维度
//		// coordinate2.setLongitude(121.485285); // 经度
//		coordinate2.setKey("3"); // 可以作为用户表的id
//
//		System.out.println(addReo(coordinate, "test"));
//		System.out.println(addReo(coordinate1, "test"));
//		System.out.println(addReo(coordinate2, "test"));
//		System.out.println(addReo(coordinate4, "test"));
//
//		Coordinate coordinate3 = new Coordinate();
//		coordinate3.setLatitude(31.245321); // 维度
//		coordinate3.setLongitude(121.485015); // 经度
//		coordinate3.setKey("4"); // 可以作为用户表的id
//
//		try {
//			List<GeoRadiusResponse> list = geoQuery(coordinate3);
//			System.out.println(JsonUtils.getMapperInstance()
//					.writeValueAsString(list));
//			for (GeoRadiusResponse geo : list) {
//				System.out.println(geo.getMemberByString()); // 主键 有主键了个人信息就很简单了
//				System.out.println(geo.getDistance() < 0.001 ? 0 : geo
//						.getDistance()); // 距离多少米
//			}
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
//		RedisUtil.close(jedis);
//	}

	/**
	 * 添加坐标 key 经度 维度 距离 return m 表示单位为米
	 */
	public static Long addReo(Coordinate coordinate, String tag) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
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
	 * 查询附近人 key 经度 维度 距离 return GeoRadiusResponse
	 */
	public static List<GeoRadiusResponse> geoQuery(Coordinate coordinate,String tag) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			// 200F GeoUnit.KM表示km
			return jedis.georadius(tag, coordinate.getLongitude(),
					coordinate.getLatitude(), 5F, GeoUnit.KM, GeoRadiusParam
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