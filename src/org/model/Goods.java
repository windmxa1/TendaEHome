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

	// Constructors

	/** default constructor */
	public Goods() {
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

	/** full constructor */
	public Goods(String name, Double price, String url, Long catalogId,
			String description, Long time, String origin) {
		this.name = name;
		this.price = price;
		this.url = url;
		this.catalogId = catalogId;
		this.description = description;
		this.time = time;
		this.origin = origin;
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

}