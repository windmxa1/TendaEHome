package org.model;

/**
 * Dish entity. @author MyEclipse Persistence Tools
 */

public class Dish implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private String pic;
	private Double price;
	private Double myPrice;
	private Integer catalogId;
	private String description;

	// Constructors

	/** default constructor */
	public Dish() {
	}

	/** minimal constructor */
	public Dish(String name, Double price, Integer catalogId) {
		this.name = name;
		this.price = price;
		this.catalogId = catalogId;
	}

	/** full constructor */
	public Dish(String name, String pic, Double price, Double myPrice,
			Integer catalogId, String description) {
		this.name = name;
		this.pic = pic;
		this.price = price;
		this.myPrice = myPrice;
		this.catalogId = catalogId;
		this.description = description;
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

	public String getPic() {
		return this.pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getMyPrice() {
		return this.myPrice;
	}

	public void setMyPrice(Double myPrice) {
		this.myPrice = myPrice;
	}

	public Integer getCatalogId() {
		return this.catalogId;
	}

	public void setCatalogId(Integer catalogId) {
		this.catalogId = catalogId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}