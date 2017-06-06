package org.model;

/**
 * Orders entity. @author MyEclipse Persistence Tools
 */

public class Orders implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userid;
	private Long time;
	private Integer state;
	private Long addressId;
	private String orderNum;
	private Integer pay_way;
	private Double total;

	// Constructors

	/** default constructor */
	public Orders() {
	}

	public Orders(Long userid, Long time, Long addressId, String orderNum) {
		this.userid = userid;
		this.time = time;
		this.addressId = addressId;
		this.orderNum = orderNum;
	}

	/** full constructor */
	public Orders(Long userid, Long time, Integer state, Long addressId,
			String orderNum) {
		this.userid = userid;
		this.time = time;
		this.state = state;
		this.addressId = addressId;
		this.orderNum = orderNum;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getTime() {
		return this.time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Long getAddressId() {
		return this.addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getPay_way() {
		return pay_way;
	}

	public void setPay_way(Integer pay_way) {
		this.pay_way = pay_way;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

}