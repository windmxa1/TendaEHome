package org.model;

/**
 * GoodsCatalog entity. @author MyEclipse Persistence Tools
 */

public class GoodsCatalog implements java.io.Serializable {

	// Fields

	private Long id;
	private String catalog;

	// Constructors

	/** default constructor */
	public GoodsCatalog() {
	}

	/** full constructor */
	public GoodsCatalog(String catalog) {
		this.catalog = catalog;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCatalog() {
		return this.catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

}