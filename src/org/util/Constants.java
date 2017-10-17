package org.util;

import java.io.File;

public class Constants {
	/**
	 * 商品描述
	 */
	public static final String body = "生态宜家-一米菜园网上超市购物";
	/**
	 * 应用id
	 */
	public static final String appid = "wxb895559396a56469";
	/**
	 * 应用秘钥
	 */
	public static final String key = "Zu34X8QLmrUbByJdxHqZm30m2NKlVWXs";
	/**
	 * 商户号
	 */
	public static final String mch_id = "1489494212";
	/**
	 * 支付结果通知地址
	 */
	public static final String notify_url = "http://39.108.82.55:8080/TendaEHome/back/orders/notifyWxPay";
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
	public static String pdfDir = "" ;
	/**
	 * pdf的网络地址
	 */
	public static String pdfUrl = "";
	/**
	 * 水印地址
	 */
	public static final String watermark = TokenUtils.rootPath
			+ "watermark.jpg";

}
