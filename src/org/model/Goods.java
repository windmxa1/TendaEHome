package org.model;

/**
 * Goods entity. @author MyEclipse Persistence Tools
 */

public class Goods implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private Double price;
	private String url;
	private Long catalogId;
	private String description;
	private Long time;
	private String origin;
	private Short state;
	private String unitName;
	private Integer unitNum;
	private Long saleNum;
	private Double originPrice;
	private Integer type;

	// Constructors

	/** default constructor */
	public Goods() {
	}

	public Goods(String name, Double price, String url, Long catalogId,
			String description, Long time, String origin, Short state,
			String unitName, Integer unitNum) {
		super();
		this.name = name;
		this.price = price;
		this.url = url;
		this.catalogId = catalogId;
		this.description = description;
		this.time = time;
		this.origin = origin;
		this.state = state;
		this.unitName = unitName;
		this.unitNum = unitNum;
	}

	/** minimal constructor */
	public Goods(String name, Double price, String url, Long catalogId,
			Long time) {
		this.name = name;
		this.price = price;
		this.url = url;
		this.catalogId = catalogId;
		this.time = time;
	}

	public Goods(String name, Double price, String url, Long catalogId,
			Long time, String unitName, Integer unitNum) {
		super();
		this.name = name;
		this.price = price;
		this.url = url;
		this.catalogId = catalogId;
		this.time = time;
		this.unitName = unitName;
		this.unitNum = unitNum;
	}

	/** full constructor */
	public Goods(String name, Double price, String url, Long catalogId,
			String description, Long time, String origin, Short state,
			String unitName, Integer unitNum, Long saleNum, Double originPrice,
			Integer type) {
		this.name = name;
		this.price = price;
		this.url = url;
		this.catalogId = catalogId;
		this.description = description;
		this.time = time;
		this.origin = origin;
		this.state = state;
		this.unitName = unitName;
		this.unitNum = unitNum;
		this.saleNum = saleNum;
		this.originPrice = originPrice;
		this.type = type;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getOrigin() {
		return this.origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public String getUnitName() {
		return this.unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public Integer getUnitNum() {
		return this.unitNum;
	}

	public void setUnitNum(Integer unitNum) {
		this.unitNum = unitNum;
	}

	public Long getSaleNum() {
		return this.saleNum;
	}

	public void setSaleNum(Long saleNum) {
		this.saleNum = saleNum;
	}

	public Double getOriginPrice() {
		return this.originPrice;
	}

	public void setOriginPrice(Double originPrice) {
		this.originPrice = originPrice;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}