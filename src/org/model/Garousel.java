package org.model;

/**
 * Garousel entity. @author MyEclipse Persistence Tools
 */

public class Garousel implements java.io.Serializable {

	// Fields

	private Long id;
	private String title;
	private String url;
	private Integer catalogId;
	private String hyperlink;
	private Long time;

	// Constructors

	/** default constructor */
	public Garousel() {
	}

	/** minimal constructor */
	public Garousel(String title, String url, Integer catalogId) {
		this.title = title;
		this.url = url;
		this.catalogId = catalogId;
	}

	/** full constructor */
	public Garousel(String title, String url, Integer catalogId,
			String hyperlink, Long time) {
		this.title = title;
		this.url = url;
		this.catalogId = catalogId;
		this.hyperlink = hyperlink;
		this.time = time;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
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

	public Integer getCatalogId() {
		return this.catalogId;
	}

	public void setCatalogId(Integer catalogId) {
		this.catalogId = catalogId;
	}

	public String getHyperlink() {
		return this.hyperlink;
	}

	public void setHyperlink(String hyperlink) {
		this.hyperlink = hyperlink;
	}

	public Long getTime() {
		return this.time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

}