package org.model;

/**
 * GarouselCatalog entity. @author MyEclipse Persistence Tools
 */

public class GarouselCatalog implements java.io.Serializable {

	// Fields

	private Integer id;
	private String garouselCatalog;

	// Constructors

	/** default constructor */
	public GarouselCatalog() {
	}

	/** full constructor */
	public GarouselCatalog(String garouselCatalog) {
		this.garouselCatalog = garouselCatalog;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGarouselCatalog() {
		return this.garouselCatalog;
	}

	public void setGarouselCatalog(String garouselCatalog) {
		this.garouselCatalog = garouselCatalog;
	}

}