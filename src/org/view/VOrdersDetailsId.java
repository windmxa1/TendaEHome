package org.view;

/**
 * VOrdersDetailsId entity. @author MyEclipse Persistence Tools
 */

public class VOrdersDetailsId implements java.io.Serializable {

	// Fields

	private Long id;
	private Long orderId;
	private Long goodsId;
	private String goodsName;
	private String goodsUrl;
	private Integer num;
	private Double prices;

	// Constructors

	/** default constructor */
	public VOrdersDetailsId() {
	}

	/** minimal constructor */
	public VOrdersDetailsId(Long id, Long orderId, Long goodsId, Integer num,
			Double prices) {
		this.id = id;
		this.orderId = orderId;
		this.goodsId = goodsId;
		this.num = num;
		this.prices = prices;
	}

	/** full constructor */
	public VOrdersDetailsId(Long id, Long orderId, Long goodsId,
			String goodsName, String goodsUrl, Integer num, Double prices) {
		this.id = id;
		this.orderId = orderId;
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodsUrl = goodsUrl;
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

	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsUrl() {
		return this.goodsUrl;
	}

	public void setGoodsUrl(String goodsUrl) {
		this.goodsUrl = goodsUrl;
	}

	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Double getPrices() {
		return this.prices;
	}

	public void setPrices(Double prices) {
		this.prices = prices;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VOrdersDetailsId))
			return false;
		VOrdersDetailsId castOther = (VOrdersDetailsId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getOrderId() == castOther.getOrderId()) || (this
						.getOrderId() != null && castOther.getOrderId() != null && this
						.getOrderId().equals(castOther.getOrderId())))
				&& ((this.getGoodsId() == castOther.getGoodsId()) || (this
						.getGoodsId() != null && castOther.getGoodsId() != null && this
						.getGoodsId().equals(castOther.getGoodsId())))
				&& ((this.getGoodsName() == castOther.getGoodsName()) || (this
						.getGoodsName() != null
						&& castOther.getGoodsName() != null && this
						.getGoodsName().equals(castOther.getGoodsName())))
				&& ((this.getGoodsUrl() == castOther.getGoodsUrl()) || (this
						.getGoodsUrl() != null
						&& castOther.getGoodsUrl() != null && this
						.getGoodsUrl().equals(castOther.getGoodsUrl())))
				&& ((this.getNum() == castOther.getNum()) || (this.getNum() != null
						&& castOther.getNum() != null && this.getNum().equals(
						castOther.getNum())))
				&& ((this.getPrices() == castOther.getPrices()) || (this
						.getPrices() != null && castOther.getPrices() != null && this
						.getPrices().equals(castOther.getPrices())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getOrderId() == null ? 0 : this.getOrderId().hashCode());
		result = 37 * result
				+ (getGoodsId() == null ? 0 : this.getGoodsId().hashCode());
		result = 37 * result
				+ (getGoodsName() == null ? 0 : this.getGoodsName().hashCode());
		result = 37 * result
				+ (getGoodsUrl() == null ? 0 : this.getGoodsUrl().hashCode());
		result = 37 * result
				+ (getNum() == null ? 0 : this.getNum().hashCode());
		result = 37 * result
				+ (getPrices() == null ? 0 : this.getPrices().hashCode());
		return result;
	}

}