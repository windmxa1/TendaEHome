package org.model;

/**
 * OrdersDetail entity. @author MyEclipse Persistence Tools
 */

public class OrdersDetail implements java.io.Serializable {

	// Fields

	private Long id;
	private Long orderId;
	private Long goodsId;
	private Double num;
	private Double prices;

	// Constructors

	/** default constructor */
	public OrdersDetail() {
	}

	/** full constructor */
	public OrdersDetail(Long orderId, Long goodsId, Double num, Double prices) {
		this.orderId = orderId;
		this.goodsId = goodsId;
		this.num = num;
		this.prices = prices;
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

	public Double getNum() {
		return this.num;
	}

	public void setNum(Double num) {
		this.num = num;
	}

	public Double getPrices() {
		return this.prices;
	}

	public void setPrices(Double prices) {
		this.prices = prices;
	}

}