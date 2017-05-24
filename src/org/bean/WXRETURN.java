package org.bean;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * 微信支付通知返回----实体类
 * @author MARSHALL
 */
@XmlRootElement(name = "xml")
public class WXRETURN {
	String return_code;
	String return_msg;
	/**
	 * 微信支付通知返回----实体类
	 * @author MARSHALL
	 */
	public WXRETURN() {
		super();
	}
	/**
	 * 微信支付通知返回----实体类
	 * @author MARSHALL
	 */
	public WXRETURN(String return_code) {
		super();
		this.return_code = return_code;
	}
	/**
	 * 微信支付通知返回----实体类
	 * @author MARSHALL
	 */
	public WXRETURN(String return_code, String return_msg) {
		super();
		this.return_code = return_code;
		this.return_msg = return_msg;
	}
	
	public String getReturn_code() {
		return return_code;
	}
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	public String getReturn_msg() {
		return return_msg;
	}
	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
	
}
