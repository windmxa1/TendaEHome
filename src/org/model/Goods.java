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

	// Constructors

	/** default constructor */
	public Goods() {
	}

	/** minimal constructor */
	public Goods(String name, Double price, String url, Long catalogId,
			Long time, String unitName, Integer unitNum) {
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
			String unitName, Integer unitNum) {
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

}