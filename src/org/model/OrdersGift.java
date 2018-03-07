package org.model;

/**
 * OrdersGift entity. @author MyEclipse Persistence Tools
 */

public class OrdersGift implements java.io.Serializable {

	// Fields

	private Long id;
	private Long orderId;
	private Long goodsId;
	private Integer actId;

	// Constructors

	/** default constructor */
	public OrdersGift() {
	}

	/** full constructor */
	public OrdersGift(Long id, Long orderId, Long goodsId, Integer actId) {
		this.id = id;
		this.orderId = orderId;
		this.goodsId = goodsId;
		this.actId = actId;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getActId() {
		return this.actId;
	}

	public void setActId(Integer actId) {
		this.actId = actId;
	}

}