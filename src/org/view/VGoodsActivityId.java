package org.view;

/**
 * VGoodsActivityId entity. @author MyEclipse Persistence Tools
 */

public class VGoodsActivityId implements java.io.Serializable {

	// Fields

	private Long id;
	private Long goodsId;
	private Long startTime;
	private Long endTime;
	private Double discount;
	private String startDate;
	private String endDate;

	// Constructors

	/** default constructor */
	public VGoodsActivityId() {
	}

	/** minimal constructor */
	public VGoodsActivityId(Long id, Long goodsId, Long startTime, Long endTime) {
		this.id = id;
		this.goodsId = goodsId;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/** full constructor */
	public VGoodsActivityId(Long id, Long goodsId, Long startTime,
			Long endTime, Double discount, String startDate, String endDate) {
		this.id = id;
		this.goodsId = goodsId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.discount = discount;
		this.startDate = startDate;
		this.endDate = endDate;
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

	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VGoodsActivityId))
			return false;
		VGoodsActivityId castOther = (VGoodsActivityId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getGoodsId() == castOther.getGoodsId()) || (this
						.getGoodsId() != null && castOther.getGoodsId() != null && this
						.getGoodsId().equals(castOther.getGoodsId())))
				&& ((this.getStartTime() == castOther.getStartTime()) || (this
						.getStartTime() != null
						&& castOther.getStartTime() != null && this
						.getStartTime().equals(castOther.getStartTime())))
				&& ((this.getEndTime() == castOther.getEndTime()) || (this
						.getEndTime() != null && castOther.getEndTime() != null && this
						.getEndTime().equals(castOther.getEndTime())))
				&& ((this.getDiscount() == castOther.getDiscount()) || (this
						.getDiscount() != null
						&& castOther.getDiscount() != null && this
						.getDiscount().equals(castOther.getDiscount())))
				&& ((this.getStartDate() == castOther.getStartDate()) || (this
						.getStartDate() != null
						&& castOther.getStartDate() != null && this
						.getStartDate().equals(castOther.getStartDate())))
				&& ((this.getEndDate() == castOther.getEndDate()) || (this
						.getEndDate() != null && castOther.getEndDate() != null && this
						.getEndDate().equals(castOther.getEndDate())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getGoodsId() == null ? 0 : this.getGoodsId().hashCode());
		result = 37 * result
				+ (getStartTime() == null ? 0 : this.getStartTime().hashCode());
		result = 37 * result
				+ (getEndTime() == null ? 0 : this.getEndTime().hashCode());
		result = 37 * result
				+ (getDiscount() == null ? 0 : this.getDiscount().hashCode());
		result = 37 * result
				+ (getStartDate() == null ? 0 : this.getStartDate().hashCode());
		result = 37 * result
				+ (getEndDate() == null ? 0 : this.getEndDate().hashCode());
		return result;
	}

}