package org.bean;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * 微信支付回调接口接收 ---实体类 ，用实体接收xml数据
 * @author MARSHALL
 */
//@RequestMapping("/notifyWxPay")
//@ResponseBody
//public Object notifyWxPay(HttpServletRequest request, WXNotify xml,
//		HttpServletResponse response) throws Exception {
//	oDao = new OrdersDaoImp();
//	ObjectMapper mapper = JsonUtils.getMapperInstance();
//	try {
//		System.out.println(mapper.writeValueAsString(xml));
//		LinkedHashMap<String, Object> map = Utils.objectToMap(xml);
//		System.out.println(mapper.writeValueAsString(map));
//		if (xml.getReturn_code().equals("SUCCESS")) {// 通信成功
//			if (xml.getResult_code().equals("SUCCESS")) {// 交易成功
//				boolean checkSign = WXAPI.makeSign(map).equals(
//						xml.getSign());// 验签防止第三方篡改数据
//				if (checkSign && oDao.updateOrder(xml.getOut_trade_no(), 2)) {// 验签成功且修改成功返回SUCCESS
//					return new WXRETURN("SUCCESS", "");
//				}
//			}
//		}
//	} catch (DocumentException e) {
//		e.printStackTrace();
//	} catch (IOException e) {
//		e.printStackTrace();
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	return new WXRETURN("FAIL", "校验失败，请重试");
//}
@XmlRootElement(name = "xml")
public class WXNotify {
	private String appid;			//应用ID		
	private String attach;			//商家数据包
	private String bank_type;		//付款银行	
	private String fee_type;		//货币种类	
	private String mch_id;			//商户号	
	private String nonce_str;		//nonce_str	
	private String openid;			//用户标识	
	private String out_trade_no;	//商户订单号	
	private String result_code;		//返回状态码	
	private String return_code;		//return_msg
//	private String sub_mch_id;		
	private String time_end;		//支付完成时间	
	private Integer total_fee;		//总金额
	private String trade_type;		//交易类型
	private String transaction_id;	//微信支付订单号
	private String sign;			//签名	
	public WXNotify() {
		super();
	}
	public WXNotify(String appid, String attach, String bank_type,
			String fee_type,  String mch_id,
			String nonce_str, String openid, String out_trade_no,
			String result_code, String return_code, String sign,
			 String time_end, Integer total_fee,
			String trade_type, String transaction_id) {
		super();
		this.appid = appid;
		this.attach = attach;
		this.bank_type = bank_type;
		this.fee_type = fee_type;
		this.mch_id = mch_id;
		this.nonce_str = nonce_str;
		this.openid = openid;
		this.out_trade_no = out_trade_no;
		this.result_code = result_code;
		this.return_code = return_code;
		this.sign = sign;
//		this.sub_mch_id = sub_mch_id;
		this.time_end = time_end;
		this.total_fee = total_fee;
		this.trade_type = trade_type;
		this.transaction_id = transaction_id;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getBank_type() {
		return bank_type;
	}

	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTime_end() {
		return time_end;
	}

	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}

	public Integer getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(Integer total_fee) {
		this.total_fee = total_fee;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	

}
