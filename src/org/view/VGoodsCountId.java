package org.view;

/**
 * VGoodsCountId entity. @author MyEclipse Persistence Tools
 */

public class VGoodsCountId implements java.io.Serializable {

	// Fields

	private Long goodsId;
	private Double count;

	// Constructors

	/** default constructor */
	public VGoodsCountId() {
	}

	/** minimal constructor */
	public VGoodsCountId(Long goodsId) {
		this.goodsId = goodsId;
	}

	/** full constructor */
	public VGoodsCountId(Long goodsId, Double count) {
		this.goodsId = goodsId;
		this.count = count;
	}

	// Property accessors

	public Long getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public Double getCount() {
		return this.count;
	}

	public void setCount(Double count) {
		this.count = count;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VGoodsCountId))
			return false;
		VGoodsCountId castOther = (VGoodsCountId) other;

		return ((this.getGoodsId() == castOther.getGoodsId()) || (this
				.getGoodsId() != null && castOther.getGoodsId() != null && this
				.getGoodsId().equals(castOther.getGoodsId())))
				&& ((this.getCount() == castOther.getCount()) || (this
						.getCount() != null && castOther.getCount() != null && this
						.getCount().equals(castOther.getCount())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getGoodsId() == null ? 0 : this.getGoodsId().hashCode());
		result = 37 * result
				+ (getCount() == null ? 0 : this.getCount().hashCode());
		return result;
	}

}