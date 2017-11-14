package org.model;

/**
 * GoodsCatalog entity. @author MyEclipse Persistence Tools
 */

public class GoodsCatalog implements java.io.Serializable {

	// Fields

	private Long id;
	private String catalog;
	private String url;

	// Constructors

	/** default constructor */
	public GoodsCatalog() {
	}

	/** minimal constructor */
	public GoodsCatalog(String catalog) {
		this.catalog = catalog;
	}

	/** full constructor */
	public GoodsCatalog(String catalog, String url) {
		this.catalog = catalog;
		this.url = url;
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

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}