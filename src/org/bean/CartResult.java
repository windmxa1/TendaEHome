package org.bean;

import java.util.List;

public class CartResult {
	private List<CartBean> data;
	private String msg;
	private Integer code;
	public List<CartBean> getData() {
		return data;
	}
	public void setData(List<CartBean> data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
}
