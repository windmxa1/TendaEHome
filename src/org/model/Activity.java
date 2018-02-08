package org.model;

import java.sql.Timestamp;

/**
 * Activity entity. @author MyEclipse Persistence Tools
 */

public class Activity implements java.io.Serializable {

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

	// Constructors

	/** default constructor */
	public Activity() {
	}

	/** minimal constructor */
	public Activity(Timestamp startDate, Timestamp endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}

	/** full constructor */
	public Activity(String title, String url, Integer range,
			Timestamp startDate, Timestamp endDate, Integer useType,
			Integer userGroupId, Double maxPrice, Double minPrice,
			Integer type, Integer num) {
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

}