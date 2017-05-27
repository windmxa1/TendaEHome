package org.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

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
	// Integer total_fee = (int) (100 * 0.00);
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
	 * 发送支付请求获取预付单号
	 */
	public static LinkedHashMap<String, Object> doPay(String out_trade_no,
			Integer total_fee, String spbill_create_ip) {
		// 随机字符串
		String nonce_str = UUID.randomUUID().toString().trim().replace("-", "");

		String xml = formatData(nonce_str, out_trade_no, total_fee,
				spbill_create_ip);
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
					String appid = root.elementText("appid");
					String nonceStr = UUID.randomUUID().toString().trim()
							.replace("-", "");
					String Package = "Sign=WXPay";
					String partnerId = root.elementText("mch_id");
					String prepayId = root.elementText("prepayId");
					String timeStamp = System.currentTimeMillis() / 1000 + "";
					
					LinkedHashMap<String, Object> data = new LinkedHashMap<>();
					data.put("appid", appid);
					data.put("nonceStr", nonceStr);
					data.put("package", Package);
					data.put("partnerId", partnerId);
					data.put("prepayId", prepayId);
					data.put("timeStamp", timeStamp);
					String sign = makeSign(data);
					data.put("sign", sign);
					return data;
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
	 * 
	 * @param pathUrl
	 * @param requestString
	 * @return
	 */
	private static  String connect(String pathUrl, String requestString) {
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
	private static String formatData(String nonce_str, String out_trade_no,
			Integer total_fee, String spbill_create_ip) {
		LinkedHashMap<String, Object> data = new LinkedHashMap<>();
		// 应用id
		String appid = Constants.appid;
		// 商户号
		String mch_id = Constants.mch_id;
		// 通知地址
		String notify_url = Constants.notify_url;
		// 支付类型 APP
		String trade_type = Constants.trade_type;
		// 商品描述 如：腾讯充值中心-QQ会员充值
		String body = Constants.body;

		// String temp = "appid=" + appid + "&" + "body=" + body + "&" +
		// "mch_id="
		// + mch_id + "&" + "nonce_str=" + nonce_str + "&" + "notify_url="
		// + notify_url + "&" + "out_trade_no=" + out_trade_no + "&"
		// + "spbill_create_ip=" + spbill_create_ip + "&" + "total_fee="
		// + total_fee + "&" + "trade_type=" + trade_type;
		// temp = temp + "&key=" + key;

		data.put("appid", appid);
		data.put("body", body);
		data.put("mch_id", mch_id);
		data.put("nonce_str", nonce_str);
		data.put("notify_url", notify_url);
		data.put("out_trade_no", out_trade_no);
		data.put("spbill_create_ip", spbill_create_ip);
		data.put("total_fee", total_fee);
		data.put("trade_type", trade_type);
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
