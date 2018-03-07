package org.view;

/**
 * VOrdersGiftId entity. @author MyEclipse Persistence Tools
 */

public class VOrdersGiftId implements java.io.Serializable {

	// Fields

	private Long goodsId;
	private String name;
	private Long orderId;
	private String goodsUrl;
	private String actName;

	// Constructors

	/** default constructor */
	public VOrdersGiftId() {
	}

	/** minimal constructor */
	public VOrdersGiftId(Long orderId) {
		this.orderId = orderId;
	}

	/** full constructor */
	public VOrdersGiftId(Long goodsId, String name, Long orderId,
			String goodsUrl, String actName) {
		this.goodsId = goodsId;
		this.name = name;
		this.orderId = orderId;
		this.goodsUrl = goodsUrl;
		this.actName = actName;
	}

	// Property accessors

	public Long getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getGoodsUrl() {
		return this.goodsUrl;
	}

	public void setGoodsUrl(String goodsUrl) {
		this.goodsUrl = goodsUrl;
	}

	public String getActName() {
		return this.actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VOrdersGiftId))
			return false;
		VOrdersGiftId castOther = (VOrdersGiftId) other;

		return ((this.getGoodsId() == castOther.getGoodsId()) || (this
				.getGoodsId() != null && castOther.getGoodsId() != null && this
				.getGoodsId().equals(castOther.getGoodsId())))
				&& ((this.getName() == castOther.getName()) || (this.getName() != null
						&& castOther.getName() != null && this.getName()
						.equals(castOther.getName())))
				&& ((this.getOrderId() == castOther.getOrderId()) || (this
						.getOrderId() != null && castOther.getOrderId() != null && this
						.getOrderId().equals(castOther.getOrderId())))
				&& ((this.getGoodsUrl() == castOther.getGoodsUrl()) || (this
						.getGoodsUrl() != null
						&& castOther.getGoodsUrl() != null && this
						.getGoodsUrl().equals(castOther.getGoodsUrl())))
				&& ((this.getActName() == castOther.getActName()) || (this
						.getActName() != null && castOther.getActName() != null && this
						.getActName().equals(castOther.getActName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getGoodsId() == null ? 0 : this.getGoodsId().hashCode());
		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		result = 37 * result
				+ (getOrderId() == null ? 0 : this.getOrderId().hashCode());
		result = 37 * result
				+ (getGoodsUrl() == null ? 0 : this.getGoodsUrl().hashCode());
		result = 37 * result
				+ (getActName() == null ? 0 : this.getActName().hashCode());
		return result;
	}

}