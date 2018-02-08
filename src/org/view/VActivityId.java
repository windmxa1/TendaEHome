package org.view;

import java.sql.Timestamp;

/**
 * VActivityId entity. @author MyEclipse Persistence Tools
 */

public class VActivityId implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String url;
	private Integer range;
	private Timestamp startDate;
	private Timestamp endDate;
	private Integer useType;
	private Integer userGroupId;
	private Double maxPrice;
	private Double minPrice;
	private Integer type;
	private Integer num;
	private String gift;
	private String goods;
	/**
	 * when type = 0 then '满减' 1 THEN '满赠' ELSE '折扣'
	 */
	private String typeName;

	// Constructors

	/** default constructor */
	public VActivityId() {
	}

	/** minimal constructor */
	public VActivityId(Integer id, Timestamp startDate, Timestamp endDate) {
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	/** full constructor */
	public VActivityId(Integer id, String title, String url, Integer range,
			Timestamp startDate, Timestamp endDate, Integer useType,
			Integer userGroupId, Double maxPrice, Double minPrice,
			Integer type, Integer num, String gift, String goods,
			String typeName) {
		this.id = id;
		this.title = title;
		this.url = url;
		this.range = range;
		this.startDate = startDate;
		this.endDate = endDate;
		this.useType = useType;
		this.userGroupId = userGroupId;
		this.maxPrice = maxPrice;
		this.minPrice = minPrice;
		this.type = type;
		this.num = num;
		this.gift = gift;
		this.goods = goods;
		this.typeName = typeName;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getRange() {
		return this.range;
	}

	public void setRange(Integer range) {
		this.range = range;
	}

	public Timestamp getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public Integer getUseType() {
		return this.useType;
	}

	public void setUseType(Integer useType) {
		this.useType = useType;
	}

	public Integer getUserGroupId() {
		return this.userGroupId;
	}

	public void setUserGroupId(Integer userGroupId) {
		this.userGroupId = userGroupId;
	}

	public Double getMaxPrice() {
		return this.maxPrice;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Double getMinPrice() {
		return this.minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getGift() {
		return this.gift;
	}

	public void setGift(String gift) {
		this.gift = gift;
	}

	public String getGoods() {
		return this.goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VActivityId))
			return false;
		VActivityId castOther = (VActivityId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getTitle() == castOther.getTitle()) || (this
						.getTitle() != null && castOther.getTitle() != null && this
						.getTitle().equals(castOther.getTitle())))
				&& ((this.getUrl() == castOther.getUrl()) || (this.getUrl() != null
						&& castOther.getUrl() != null && this.getUrl().equals(
						castOther.getUrl())))
				&& ((this.getRange() == castOther.getRange()) || (this
						.getRange() != null && castOther.getRange() != null && this
						.getRange().equals(castOther.getRange())))
				&& ((this.getStartDate() == castOther.getStartDate()) || (this
						.getStartDate() != null
						&& castOther.getStartDate() != null && this
						.getStartDate().equals(castOther.getStartDate())))
				&& ((this.getEndDate() == castOther.getEndDate()) || (this
						.getEndDate() != null && castOther.getEndDate() != null && this
						.getEndDate().equals(castOther.getEndDate())))
				&& ((this.getUseType() == castOther.getUseType()) || (this
						.getUseType() != null && castOther.getUseType() != null && this
						.getUseType().equals(castOther.getUseType())))
				&& ((this.getUserGroupId() == castOther.getUserGroupId()) || (this
						.getUserGroupId() != null
						&& castOther.getUserGroupId() != null && this
						.getUserGroupId().equals(castOther.getUserGroupId())))
				&& ((this.getMaxPrice() == castOther.getMaxPrice()) || (this
						.getMaxPrice() != null
						&& castOther.getMaxPrice() != null && this
						.getMaxPrice().equals(castOther.getMaxPrice())))
				&& ((this.getMinPrice() == castOther.getMinPrice()) || (this
						.getMinPrice() != null
						&& castOther.getMinPrice() != null && this
						.getMinPrice().equals(castOther.getMinPrice())))
				&& ((this.getType() == castOther.getType()) || (this.getType() != null
						&& castOther.getType() != null && this.getType()
						.equals(castOther.getType())))
				&& ((this.getNum() == castOther.getNum()) || (this.getNum() != null
						&& castOther.getNum() != null && this.getNum().equals(
						castOther.getNum())))
				&& ((this.getGift() == castOther.getGift()) || (this.getGift() != null
						&& castOther.getGift() != null && this.getGift()
						.equals(castOther.getGift())))
				&& ((this.getGoods() == castOther.getGoods()) || (this
						.getGoods() != null && castOther.getGoods() != null && this
						.getGoods().equals(castOther.getGoods())))
				&& ((this.getTypeName() == castOther.getTypeName()) || (this
						.getTypeName() != null
						&& castOther.getTypeName() != null && this
						.getTypeName().equals(castOther.getTypeName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getTitle() == null ? 0 : this.getTitle().hashCode());
		result = 37 * result
				+ (getUrl() == null ? 0 : this.getUrl().hashCode());
		result = 37 * result
				+ (getRange() == null ? 0 : this.getRange().hashCode());
		result = 37 * result
				+ (getStartDate() == null ? 0 : this.getStartDate().hashCode());
		result = 37 * result
				+ (getEndDate() == null ? 0 : this.getEndDate().hashCode());
		result = 37 * result
				+ (getUseType() == null ? 0 : this.getUseType().hashCode());
		result = 37
				* result
				+ (getUserGroupId() == null ? 0 : this.getUserGroupId()
						.hashCode());
		result = 37 * result
				+ (getMaxPrice() == null ? 0 : this.getMaxPrice().hashCode());
		result = 37 * result
				+ (getMinPrice() == null ? 0 : this.getMinPrice().hashCode());
		result = 37 * result
				+ (getType() == null ? 0 : this.getType().hashCode());
		result = 37 * result
				+ (getNum() == null ? 0 : this.getNum().hashCode());
		result = 37 * result
				+ (getGift() == null ? 0 : this.getGift().hashCode());
		result = 37 * result
				+ (getGoods() == null ? 0 : this.getGoods().hashCode());
		result = 37 * result
				+ (getTypeName() == null ? 0 : this.getTypeName().hashCode());
		return result;
	}

}