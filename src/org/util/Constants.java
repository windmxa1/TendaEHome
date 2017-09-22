package org.util;

import java.io.File;

public class Constants {
	/**
	 * 商品描述
	 */
	public static final String body = "泰达E家人 一米菜园网上超市购物";
	/**
	 * 应用id
	 */
	public static final String appid = "wx8a1727cf2f5a05c5";
	/**
	 * 应用秘钥
	 */
	public static final String key = "421cb2cdbf0abda73e4652bd87ab9c81";
	/**
	 * 商户号
	 */
	public static final String mch_id = "654321";
	/**
	 * 支付结果通知地址
	 */
	public static final String notify_url = "https://192.168.1.150:8080/TendaEHome/back/orders/notifyWxPay";
	/**
	 * 交易类型
	 */
	public static final String trade_type = "APP";
	/**
	 * 统一下单接口地址
	 */
	public static final String payUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	/**
	 * 适应任何系统的文件分隔符
	 */
	public static final String dot = File.separator;
	/**
	 * pdf在服务器的根目录
	 */
	public static String pdfDir = "D:\\Tomcat 7.0\\webapps\\TendaEHome\\pdf\\" ;
	/**
	 * pdf的网络地址
	 */
	public static String pdfUrl = "http://192.168.1.150:8080/TendaEHome/pdf";
	/**
	 * 水印地址
	 */
	public static final String watermark = TokenUtils.rootPath
			+ "watermark.jpg";

}
