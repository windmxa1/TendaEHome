package org.model;

/**
 * ScrollNews entity. @author MyEclipse Persistence Tools
 */

public class ScrollNews implements java.io.Serializable {

	// Fields

	private Integer id;
	private String content;
	private String url;

	// Constructors

	/** default constructor */
	public ScrollNews() {
	}

	/** full constructor */
	public ScrollNews(String content, String url) {
		this.content = content;
		this.url = url;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}