package org.model;

/**
 * GoodsActivity entity. @author MyEclipse Persistence Tools
 */

public class GoodsActivity implements java.io.Serializable {

	// Fields

	private Long id;
	private Long goodsId;
	private Long startTime;
	private Long endTime;
	private Double discount;

	// Constructors

	/** default constructor */
	public GoodsActivity() {
	}

	/** minimal constructor */
	public GoodsActivity(Long goodsId, Long startTime, Long endTime) {
		this.goodsId = goodsId;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/** full constructor */
	public GoodsActivity(Long goodsId, Long startTime, Long endTime,
			Double discount) {
		this.goodsId = goodsId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.discount = discount;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public Long getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public Double getDiscount() {
		return this.discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

}