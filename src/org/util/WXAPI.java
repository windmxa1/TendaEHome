package org.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class WXAPI {
	// public static void main(String[] args) throws JsonProcessingException {
	//
	// // 随机字符串
	// String nonce_str = UUID.randomUUID().toString().trim().replace("-", "");
	// // 商户订单号，同一商户下唯一
	// String out_trade_no = "321654";
	// // 订单总金额，单位为分
	// Integer total_fee = (int) (100 * 454.15);
	// // 用户端实际ip
	// String spbill_create_ip = "192.168.1.115";
	//
	// String xml = formatData(nonce_str, out_trade_no, total_fee,
	// spbill_create_ip);
	// System.out.println(xml);
	// System.out.println(connect(Constants.payUrl, xml));
	//
	// }
	/**
	 * 签名校验
	 */
	public static Boolean validSign(Map map) {
		String appid = (String) map.get("appid");// 应用ID
		// String attach = (String) map.get("attach");// 商家数据包
		String bank_type = (String) map.get("bank_type");// 付款银行
		String cash_fee = (String) map.get("cash_fee");// 现金支付金额
		String fee_type = (String) map.get("fee_type");// 货币种类
		String is_subscribe = (String) map.get("is_subscribe");// 是否关注公众账号
		String mch_id = (String) map.get("mch_id");// 商户号
		String nonce_str = (String) map.get("nonce_str");// 随机字符串
		String openid = (String) map.get("openid");// 用户标识
		String out_trade_no = (String) map.get("out_trade_no");// 获取商户订单号
		String result_code = (String) map.get("result_code");// 业务结果
		String return_code = (String) map.get("return_code");// SUCCESS/FAIL
		String time_end = (String) map.get("time_end");// 支付完成时间
		String total_fee = (String) map.get("total_fee");// 获取订单金额
		String trade_type = (String) map.get("trade_type");// 交易类型
		String transaction_id = (String) map.get("transaction_id");// 微信支付订单号

		LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
		linkedHashMap.put("appid", appid);
		// linkedHashMap.put("attach", attach);
		linkedHashMap.put("bank_type", bank_type);
		linkedHashMap.put("cash_fee", cash_fee);
		linkedHashMap.put("fee_type", fee_type);
		linkedHashMap.put("is_subscribe", is_subscribe);
		linkedHashMap.put("mch_id", mch_id);
		linkedHashMap.put("nonce_str", nonce_str);
		linkedHashMap.put("openid", openid);
		linkedHashMap.put("out_trade_no", out_trade_no);
		linkedHashMap.put("result_code", result_code);
		linkedHashMap.put("return_code", return_code);
		linkedHashMap.put("time_end", time_end);
		linkedHashMap.put("total_fee", total_fee);
		linkedHashMap.put("trade_type", trade_type);
		linkedHashMap.put("transaction_id", transaction_id);

		String sign = (String) map.get("sign");// 获取签名
		Boolean isOk = makeSign(linkedHashMap).equals(sign);
		return isOk;
	}

	/**
	 * 返回xml格式的数据给微信
	 */
	public static String setXml(String return_code, String return_msg) {
		return "<xml><return_code><![CDATA[" + return_code + "]]>"
				+ "</return_code><return_msg><![CDATA[" + return_msg
				+ "]]></return_msg></xml>";
	}

	/**
	 * 发送退款请求
	 * 
	 * @param orderNum
	 *            订单编号
	 * @param refundId
	 *            退款单编号
	 * @param refund_fee
	 *            退款金额，单位为分
	 * @param total_fee
	 *            订单金额，单位为分
	 * @return
	 */
	public static boolean doRefund(String orderNum, String refundId,
			Integer refund_fee, Integer total_fee,String refund_desc) {
		// 随机字符串
		String nonce_str = UUID.randomUUID().toString().trim().replace("-", "");
		LinkedHashMap<String, Object> data = new LinkedHashMap<>();
		data.put("appid", Constants.appid);
		data.put("mch_id", Constants.mch_id);
		data.put("nonce_str", nonce_str);
		data.put("out_refund_no", refundId);
		data.put("out_trade_no", orderNum);
		data.put("refund_desc", refund_desc);
		data.put("refund_fee", refund_fee);
		data.put("total_fee", total_fee);
		String xml = formatData(data);
		if (xml == null) {
			return false;
		}
		System.out.println(xml);
		try {
			String resultXml = clientConnect(Constants.refundUrl, xml);
			System.out.println(resultXml);
			if (resultXml.equals("")) {
				return false;
			}
			Document doc = DocumentHelper.parseText(resultXml);
			Element root = doc.getRootElement();
			if (root.elementText("return_code").equals("SUCCESS")) {// 通讯结果
				if (root.elementText("result_code").equals("SUCCESS")) {// 业务结果
					System.out.println("申请退款成功");
					return true;
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 发送支付请求获取预付单号
	 */
	public static LinkedHashMap<String, Object> doPay(String out_trade_no,
			Integer total_fee, String spbill_create_ip) {
		// 随机字符串
		String nonce_str = UUID.randomUUID().toString().trim().replace("-", "");
		LinkedHashMap<String, Object> data = new LinkedHashMap<>();

		data.put("appid", Constants.appid);
		data.put("body", Constants.body);
		data.put("mch_id", Constants.mch_id);
		data.put("nonce_str", nonce_str);
		data.put("notify_url", Constants.notify_url);
		data.put("out_trade_no", out_trade_no);
		data.put("spbill_create_ip", spbill_create_ip);
		data.put("total_fee", total_fee);
		data.put("trade_type", Constants.trade_type);
		String xml = formatData(data);
		if (xml == null) {
			return null;
		}
		System.out.println(xml);
		String resultXml = connect(Constants.payUrl, xml);
		try {
			Document doc = DocumentHelper.parseText(resultXml);
			Element root = doc.getRootElement();
			if (root.elementText("return_code").equals("SUCCESS")) {
				if (root.elementText("result_code").equals("SUCCESS")) {
					String APPID = root.elementText("appid");
					String nonceStr = UUID.randomUUID().toString().trim()
							.replace("-", "");
					String Package = "Sign=WXPay";
					String partnerId = root.elementText("mch_id");
					String prepayId = root.elementText("prepay_id");
					String timeStamp = System.currentTimeMillis() / 1000 + "";

					LinkedHashMap<String, Object> return_data = new LinkedHashMap<>();
					return_data.put("appid", APPID);
					return_data.put("nonceStr", nonceStr);
					return_data.put("package", Package);
					return_data.put("partnerId", partnerId);
					return_data.put("prepayId", prepayId);
					return_data.put("timeStamp", timeStamp);
					String sign = makeSign(return_data);
					return_data.put("sign", sign);
					return return_data;
				}
			}
			System.out.println(resultXml);
			return null;
		} catch (DocumentException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 发送网络请求
	 */
	private static String clientConnect(String pathUrl, String requestString)
			throws KeyStoreException, NoSuchAlgorithmException,
			CertificateException, IOException, KeyManagementException,
			UnrecoverableKeyException {
		// 指定读取证书格式为PKCS12
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		// 读取本机存放的PKCS12证书文件
		FileInputStream instream = new FileInputStream(new File(
				"/opt/apiclient_cert.p12"));
		try {
			// 指定PKCS12的密码(商户ID)
			keyStore.load(instream, Constants.mch_id.toCharArray());
		} finally {
			instream.close();
		}
		SSLContext sslcontext = SSLContexts.custom()
				.loadKeyMaterial(keyStore, Constants.mch_id.toCharArray()).build();
		// 指定TLS版本
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
				sslcontext, new String[] { "TLSv1" }, null,
				SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
		// 设置httpclient的SSLSocketFactory
		CloseableHttpClient httpclient = HttpClients.custom()
				.setSSLSocketFactory(sslsf).build();
		String result = "";
		try {

			HttpPost httpost = new HttpPost(pathUrl); // 设置响应头信息
			httpost.addHeader("Connection", "keep-alive");
			httpost.addHeader("Accept", "*/*");
			httpost.addHeader("Content-Type",
					"application/x-www-form-urlencoded; charset=UTF-8");
			httpost.addHeader("Host", "api.mch.weixin.qq.com");
			httpost.addHeader("X-Requested-With", "XMLHttpRequest");
			httpost.addHeader("Cache-Control", "max-age=0");
			httpost.addHeader("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
			httpost.setEntity(new StringEntity(requestString, "UTF-8"));

			CloseableHttpResponse response = httpclient.execute(httpost);
			try {
				HttpEntity entity = response.getEntity();

				System.out.println("----------------------------------------");
				System.out.println(response.getStatusLine());
				if (entity != null) {
					result = EntityUtils
							.toString(response.getEntity(), "UTF-8");
				}
				EntityUtils.consume(entity);
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
		return result;
	}

	/**
	 * 发送网络请求
	 * 
	 * @param pathUrl
	 * @param requestString
	 * @return
	 */
	private static String connect(String pathUrl, String requestString) {
		// 建立连接
		URL url;
		try {
			url = new URL(pathUrl);
			HttpURLConnection httpConn = (HttpURLConnection) url
					.openConnection();
			httpConn.setDoOutput(true);// 使用 URL 连接进行输出
			httpConn.setDoInput(true);// 使用 URL 连接进行输入
			httpConn.setRequestMethod("POST");// 设置URL请求方法
			httpConn.setUseCaches(false);// 忽略缓存

			// 设置请求属性
			// 获得数据字节数据，请求数据流的编码，必须和下面服务器端处理请求流的编码一致
			// requestString = "GRM=20437182687&PASS=123456";
			byte[] requestStringBytes = requestString.getBytes("utf-8");
			httpConn.setRequestProperty("Content-length", ""
					+ requestStringBytes.length);
			httpConn.setRequestProperty("Accept", "application/json");
			httpConn.setRequestProperty("Content-Type", "application/json");
			httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
			httpConn.setRequestProperty("Charset", "utf-8");
			// 建立输出流，并写入数据
			OutputStream outputStream = httpConn.getOutputStream();
			outputStream.write(requestStringBytes);
			outputStream.close();
			// 获得响应状态
			StringBuffer sb = new StringBuffer();
			int responseCode = httpConn.getResponseCode();
			if (HttpURLConnection.HTTP_OK == responseCode) {// 连接成功
				// 当正确响应时处理数据
				String readLine;
				BufferedReader responseReader;
				// 处理响应流，必须与服务器响应流输出的编码一致
				responseReader = new BufferedReader(new InputStreamReader(
						httpConn.getInputStream(), "utf-8"));
				while ((readLine = responseReader.readLine()) != null) {
					sb.append(readLine).append("\n");
				}
				System.out.println(sb.toString());
				responseReader.close();
			} else {
				// StringBuffer sb = new StringBuffer();
				String readLine;
				BufferedReader responseReader;
				// 处理响应流，必须与服务器响应流输出的编码一致
				responseReader = new BufferedReader(new InputStreamReader(
						httpConn.getErrorStream(), "utf-8"));
				while ((readLine = responseReader.readLine()) != null) {
					sb.append(readLine).append("\n");
				}
				System.out.println(responseCode);
				System.out.println("错误信息:" + sb.toString());
			}
			return sb.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将数据格式化成xml字符串，只用于转换微信统一下单接口的参数
	 * 
	 * @param nonce_str
	 * @param out_trade_no
	 * @param total_fee
	 * @param spbill_create_ip
	 * @return
	 */
	private static String formatData(LinkedHashMap<String, Object> data) {
		// 签名：发送的所有参数键值对(key=value)用&拼接成stringA之后再拼接秘钥,
		// 拼接完成进行md5运算之后使用toUpperCase全大写
		String sign = makeSign(data);

		data.put("sign", sign);
		try {
			return XmlUtils.formatXml(XmlUtils.map2xml(data, "xml"));
		} catch (DocumentException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 快速生成签名
	 */
	public static String makeSign(LinkedHashMap<String, Object> data) {
		data.remove("sign");
		String temp = "";
		int i = 0;
		for (Map.Entry<String, Object> entry : data.entrySet()) {
			if (i == 0) {
				temp += entry.getKey() + "=" + entry.getValue();
			} else {
				temp += "&" + entry.getKey() + "=" + entry.getValue();
			}
			i = 1;
		}
		temp += "&key=" + Constants.key;
		// 签名：发送的所有参数键值对(key=value)用&拼接成stringA之后再拼接秘钥,
		// 拼接完成进行md5运算之后使用toUpperCase全大写
		return MD5.string2MD5(temp).toUpperCase();
	}
}
