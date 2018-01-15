package org.model;

/**
 * ScrollNews entity. @author MyEclipse Persistence Tools
 */

public class ScrollNews implements java.io.Serializable {

	// Fields

	private Integer id;
	private String content;
	private String url;
	private Integer usage;
	private Integer weekday;

	// Constructors

	/** default constructor */
	public ScrollNews() {
	}

	/** minimal constructor */
	public ScrollNews(String content, String url) {
		this.content = content;
		this.url = url;
	}

	/** full constructor */
	public ScrollNews(String content, String url, Integer usage, Integer weekday) {
		this.content = content;
		this.url = url;
		this.usage = usage;
		this.weekday = weekday;
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

	public Integer getUsage() {
		return this.usage;
	}

	public void setUsage(Integer usage) {
		this.usage = usage;
	}

	public Integer getWeekday() {
		return this.weekday;
	}

	public void setWeekday(Integer weekday) {
		this.weekday = weekday;
	}

}