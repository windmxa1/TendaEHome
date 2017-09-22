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
	private String address;
	private String orderNum;
	private String staffId;
	private Integer isExport;

	// Constructors

	/** default constructor */
	public Orders() {
	}

	/** minimal constructor */
	public Orders(Long userid, Long time, String address, String orderNum) {
		this.userid = userid;
		this.time = time;
		this.address = address;
		this.orderNum = orderNum;
	}

	public Orders(Long userid, Long time, Integer state, String address,
			String orderNum) {
		this.userid = userid;
		this.time = time;
		this.state = state;
		this.address = address;
		this.orderNum = orderNum;
	}
	/** full constructor */
	public Orders(Long userid, Long time, Integer state, String address,
			String orderNum, String staffId, Integer isExport) {
		this.userid = userid;
		this.time = time;
		this.state = state;
		this.address = address;
		this.orderNum = orderNum;
		this.staffId = staffId;
		this.isExport = isExport;
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

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getStaffId() {
		return this.staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public Integer getIsExport() {
		return this.isExport;
	}

	public void setIsExport(Integer isExport) {
		this.isExport = isExport;
	}

}