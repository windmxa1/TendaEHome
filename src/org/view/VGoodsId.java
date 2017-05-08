package org.view;

/**
 * VGoodsId entity. @author MyEclipse Persistence Tools
 */

public class VGoodsId implements java.io.Serializable {

	// Fields

	private Long goodsId;	//商品编号
	private String name;	//商品名
	private Double price;	//商品价格
	private String url;		
	private Long catalogId;		
	private String description;	//商品描述
	private Long time;			
	private String createTime;	//上架时间
	private String catalog;		//目录
	private Double count;		//已售
	private Double disPrice;	//折扣价格
	private String startDate;	//折扣起始时间
	private String endDate;		//折扣结束时间
	private String goodsUrl;	//商品图片地址
	private String origin;		//商品原产地

	// Constructors

	/** default constructor */
	public VGoodsId() {
	}

	/** minimal constructor */
	public VGoodsId(Long goodsId, String name, Double price, String url,
			Long catalogId, Long time) {
		this.goodsId = goodsId;
		this.name = name;
		this.price = price;
		this.url = url;
		this.catalogId = catalogId;
		this.time = time;
	}

	/** full constructor */
	public VGoodsId(Long goodsId, String name, Double price, String url,
			Long catalogId, String description, Long time, String createTime,
			String catalog, Double count, Double disPrice, String startDate,
			String endDate, String goodsUrl, String origin) {
		this.goodsId = goodsId;
		this.name = name;
		this.price = price;
		this.url = url;
		this.catalogId = catalogId;
		this.description = description;
		this.time = time;
		this.createTime = createTime;
		this.catalog = catalog;
		this.count = count;
		this.disPrice = disPrice;
		this.startDate = startDate;
		this.endDate = endDate;
		this.goodsUrl = goodsUrl;
		this.origin = origin;
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

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getCatalogId() {
		return this.catalogId;
	}

	public void setCatalogId(Long catalogId) {
		this.catalogId = catalogId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getTime() {
		return this.time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCatalog() {
		return this.catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public Double getCount() {
		return this.count;
	}

	public void setCount(Double count) {
		this.count = count;
	}

	public Double getDisPrice() {
		return this.disPrice;
	}

	public void setDisPrice(Double disPrice) {
		this.disPrice = disPrice;
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

	public String getGoodsUrl() {
		return this.goodsUrl;
	}

	public void setGoodsUrl(String goodsUrl) {
		this.goodsUrl = goodsUrl;
	}

	public String getOrigin() {
		return this.origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VGoodsId))
			return false;
		VGoodsId castOther = (VGoodsId) other;

		return ((this.getGoodsId() == castOther.getGoodsId()) || (this
				.getGoodsId() != null && castOther.getGoodsId() != null && this
				.getGoodsId().equals(castOther.getGoodsId())))
				&& ((this.getName() == castOther.getName()) || (this.getName() != null
						&& castOther.getName() != null && this.getName()
						.equals(castOther.getName())))
				&& ((this.getPrice() == castOther.getPrice()) || (this
						.getPrice() != null && castOther.getPrice() != null && this
						.getPrice().equals(castOther.getPrice())))
				&& ((this.getUrl() == castOther.getUrl()) || (this.getUrl() != null
						&& castOther.getUrl() != null && this.getUrl().equals(
						castOther.getUrl())))
				&& ((this.getCatalogId() == castOther.getCatalogId()) || (this
						.getCatalogId() != null
						&& castOther.getCatalogId() != null && this
						.getCatalogId().equals(castOther.getCatalogId())))
				&& ((this.getDescription() == castOther.getDescription()) || (this
						.getDescription() != null
						&& castOther.getDescription() != null && this
						.getDescription().equals(castOther.getDescription())))
				&& ((this.getTime() == castOther.getTime()) || (this.getTime() != null
						&& castOther.getTime() != null && this.getTime()
						.equals(castOther.getTime())))
				&& ((this.getCreateTime() == castOther.getCreateTime()) || (this
						.getCreateTime() != null
						&& castOther.getCreateTime() != null && this
						.getCreateTime().equals(castOther.getCreateTime())))
				&& ((this.getCatalog() == castOther.getCatalog()) || (this
						.getCatalog() != null && castOther.getCatalog() != null && this
						.getCatalog().equals(castOther.getCatalog())))
				&& ((this.getCount() == castOther.getCount()) || (this
						.getCount() != null && castOther.getCount() != null && this
						.getCount().equals(castOther.getCount())))
				&& ((this.getDisPrice() == castOther.getDisPrice()) || (this
						.getDisPrice() != null
						&& castOther.getDisPrice() != null && this
						.getDisPrice().equals(castOther.getDisPrice())))
				&& ((this.getStartDate() == castOther.getStartDate()) || (this
						.getStartDate() != null
						&& castOther.getStartDate() != null && this
						.getStartDate().equals(castOther.getStartDate())))
				&& ((this.getEndDate() == castOther.getEndDate()) || (this
						.getEndDate() != null && castOther.getEndDate() != null && this
						.getEndDate().equals(castOther.getEndDate())))
				&& ((this.getGoodsUrl() == castOther.getGoodsUrl()) || (this
						.getGoodsUrl() != null
						&& castOther.getGoodsUrl() != null && this
						.getGoodsUrl().equals(castOther.getGoodsUrl())))
				&& ((this.getOrigin() == castOther.getOrigin()) || (this
						.getOrigin() != null && castOther.getOrigin() != null && this
						.getOrigin().equals(castOther.getOrigin())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getGoodsId() == null ? 0 : this.getGoodsId().hashCode());
		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		result = 37 * result
				+ (getPrice() == null ? 0 : this.getPrice().hashCode());
		result = 37 * result
				+ (getUrl() == null ? 0 : this.getUrl().hashCode());
		result = 37 * result
				+ (getCatalogId() == null ? 0 : this.getCatalogId().hashCode());
		result = 37
				* result
				+ (getDescription() == null ? 0 : this.getDescription()
						.hashCode());
		result = 37 * result
				+ (getTime() == null ? 0 : this.getTime().hashCode());
		result = 37
				* result
				+ (getCreateTime() == null ? 0 : this.getCreateTime()
						.hashCode());
		result = 37 * result
				+ (getCatalog() == null ? 0 : this.getCatalog().hashCode());
		result = 37 * result
				+ (getCount() == null ? 0 : this.getCount().hashCode());
		result = 37 * result
				+ (getDisPrice() == null ? 0 : this.getDisPrice().hashCode());
		result = 37 * result
				+ (getStartDate() == null ? 0 : this.getStartDate().hashCode());
		result = 37 * result
				+ (getEndDate() == null ? 0 : this.getEndDate().hashCode());
		result = 37 * result
				+ (getGoodsUrl() == null ? 0 : this.getGoodsUrl().hashCode());
		result = 37 * result
				+ (getOrigin() == null ? 0 : this.getOrigin().hashCode());
		return result;
	}

}