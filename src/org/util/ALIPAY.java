package org.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import org.dao.OrdersDao;
import org.dao.imp.OrdersDaoImp;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;

public class ALIPAY {
	public final static String APP_ID = "2017092708956547";
	public final static String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCd2XvinyIgm3TZbRsJwMoSALxGOpzJbj9PbUBdpjWtLQX3XhofOZZ3MB2bnynOvfUwCjo4IZQBYnnfs9h2aE9iukCtU74Rfabv1c75byfrSn38miZ9UVAIHA/6mabRzqhFOBF6YzRD3+olP2H+hRGTM1eLJEE3Zf+dphWbX3dQ0+ljkLsS+fdfI35xvy0WtcmJ9giNrG0SZffkpEshwO/92CUd/6PR6As9NDEhx+gwqbDo28+WJ0nLxoM5APMsiXG6niMywDhDITpy7CSoV2NmfyYM4gWL3rZZ4dW0p7T+7BUsjRCnbO/WaYvD++ay+f1Q7o4QMrNCvvbbA50HtwDRAgMBAAECggEASInaQZGJmFx4PTbDdx7ufYly+/8RcAvG8lQtgRvRjOyk0kGfVXwcZldpM8vX5Zcr5tC8YnEj1+MOSLwYHIng+WjIMh7KuLT6kzq5K61mNd18uimIyIwmcCOdQDd+DuqeL3kNpyWRriNnMYjIGrPCGNg+QcX+ZlvsNOFPCqC7bxvI7mzKkjJG5hy2wmEsR37TqN/HrOdDi1ZtcO4321+6K7ashW4j4pjfuwHYLHteHa3dNjgowr7S0p9OY3QjAbyWBwDio7fpAWpkBdGz4KhZy1LoJPAVgxoaqNu4Mw6nUgWxrKqDaR2oTpLgz8n9WDE5SpOidHHlRqVpCBAdAb1JdQKBgQDj2bCMGiHEZskB2AhxJFJakZbzy53wlJtRu7SqR90aput5U9tONL0jTyTB1dgzz+Ko1abe0AK5xwOUkhVhk2U9DgDqoCmYAAVOCuMUPc2Jw3hzQEqkxQLciiMPMY3vG9qGi1o5ZghpxpBXiLuMBnyAzHVqZ6rcqftu5hxiMjVB1wKBgQCxWdofdZYEEBStAhi8VQg5pq5Qq4XrJm96XSoAOgWmpPDEIhz6pa/UPGson5LTlx6KtrAKU4wBRXAw/U/D1dpq7QmrWpwpeCWXyzWZYpJlwbBZf2HZOzZp7rMc2bF7Jct+IuJgoUqI942o3YNXzsQ/DenM6czwScY2HOU29YzNlwKBgQCW/PneTa7UvJw1KZimiUA9nj7e/a6C76dhaTbWpFqjv7tJIkWwtIglrtuXy4LDCt6TrUkwW72CqJv5xzh9+bPb2rzGvi3x+QmK5vIFxtK+mRbnoNIWECvOQ4fqGJeKvPV5tTyrzq6Ckb+zEfveVaoJm/Ou+FWkdswUeE6Uz9q07wKBgAIdDQFk+FWyD10EcmEELmXpe5hQgvcLbTarF44LIvgabRgZQ/kkslS4J5rDVA+bdGy4wp5M4pU9S5n52tJ9oO23R284VDYWTXRioo/iYdY+mahnYTYig4J9qrrESV4rVCJ7rfDy2x2IvbJueVs5hpMtykR74MpYRrlsXLrr8DKfAoGAb5tE4C4mEViRQgPFJrNNtsmWm4rAXPnZrxf27K6udM09ttWoorNwAvq/zDkKyX7mvkQ37Yqx5pcFo00wKMh/3UitogHtnWiUHn8PbWCTkrjA2kRdABLOMxjmZpXnN5/lzvC60dNixkl/nhIPbPdSePZAhxCBa58XDGFa8WPKbgA=";
	public final static String CHARSET = "UTF-8";
	public final static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhRyLeDhW1mPD2EZpu2jLB1MzkkdGsQ2x6q6h5FMb76WbW5H7NxxRXklO0Rbh/FwgtpFogahPBn8h8k8VfttVa2lzs5HTNym9hJiDz/IOiO63aCoTgepg1iNx37o0c1SuzkepKG1ifNZWsp1Ub7F7um2VN23V7nXkUigD94cVsk7n6XF35k0nDY/dKgl1CJfDfclYq24cFbZcY9pSpd1Dm3UlmWkP/j+YcmDbvuczfBeMG6g6AOVxQLSf+qnd2xRYCFohOu52qrAayi3VjVTdJ9VYPqratoKaNxfQpS+Q7WFfzTiXILeiNmTq77JdbFzfZQr7Y4jI2xubCT+r9UJv4wIDAQAB";
	public final static String NotifyUrl = "http://39.108.82.55:8080/TendaEHome/back/orders/notifyAliPay";
	// public final static String GATEWAY =
	// "https://openapi.alipaydev.com/gateway.do";

	public final static String GATEWAY = "https://openapi.alipay.com/gateway.do";

	// public static void main(String[] args) throws
	// UnsupportedEncodingException {
	// doPay("123123", "1.23");
	// }

	/**
	 * 获取支付信息
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public static String doPay(String orderNum, String total_fee)
			throws UnsupportedEncodingException {
		// 实例化客户端
		AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY, APP_ID,
				APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");
		// 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		// SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		model.setBody("生态宜家-一米菜园网上超市购物");
		model.setSubject("生态宜家");
		model.setOutTradeNo(orderNum);
//		model.setTimeoutExpress("30m");//设置支付时限
		model.setTotalAmount(total_fee);
		model.setProductCode("QUICK_MSECURITY_PAY");
		request.setBizModel(model);
		request.setNotifyUrl(NotifyUrl);
		
		try {
			// 这里和普通的接口调用不同，使用的是sdkExecute
			AlipayTradeAppPayResponse response = alipayClient
					.sdkExecute(request);
			System.out.println(URLDecoder.decode(response.getBody(), "UTF-8"));
			// 就是orderString
			// 可以直接给客户端请求，无需再做处理。
			return response.getBody();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 支付宝退款
	 */
	public static String refund(String out_trade_no, String refund_amount) {
		AlipayClient alipayClient = new DefaultAlipayClient(
				"https://openapi.alipay.com/gateway.do", APP_ID,
				APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");
		AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();

		request.setBizContent("{" + "\"out_trade_no\":\"" + out_trade_no
				+ "\"," + "\"refund_amount\":" + refund_amount + ","
				+ "\"refund_reason\":\"正常退款\"" + "  }");
		AlipayTradeRefundResponse response;
		try {
			response = alipayClient.execute(request);
			if (response.isSuccess()) {
				System.out.println("调用成功");
			} else {
				System.out.println("调用失败");
			}
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/*
	 * 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号；
	 * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额）；
	 * 3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据对应的操作方
	 * （有的时候，一个商户可能有多个seller_id/seller_email）； 4、验证app_id是否为该商户本身。
	 * 上述1、2、3、4有任何一个验证不通过，则表明同步校验结果是无效的，只有全部验证通过后，才可以认定买家付款成功。
	 */
	/**
	 * 参数校验
	 */
	public static Boolean isValid(Map<String, String> params) {
		if (APP_ID.equals(params.get("app_id"))) {
			OrdersDao oDao = new OrdersDaoImp();
			Double total = oDao.getTotal("" + params.get("out_trade_no"));
			if (total == Double.parseDouble((params.get("total_amount")))) {
				if (oDao.updateOrder("" + params.get("out_trade_no"), 2, 1)) {// 验签成功且修改成功返回SUCCESS
					return true;
				}
			} else {
				System.out.println("订单总价错误,参数总价为：" + params.get("total_fee")
						+ "," + "订单实际总价为：" + total);
			}
		} else {
			System.out.println("appid不正确");
		}
		return false;
	}
}
