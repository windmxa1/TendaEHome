package org.model;


/**
 * Tv entity. @author MyEclipse Persistence Tools
 */

public class Tv implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String imgUrl;
	private String tvUrl;
	private Long time;
	private Integer available;

	// Constructors

	/** default constructor */
	public Tv() {
	}

	/** full constructor */
	public Tv(String name, String imgUrl, String tvUrl, Long time,
			Integer available) {
		this.name = name;
		this.imgUrl = imgUrl;
		this.tvUrl = tvUrl;
		this.time = time;
		this.available = available;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getTvUrl() {
		return this.tvUrl;
	}

	public void setTvUrl(String tvUrl) {
		this.tvUrl = tvUrl;
	}

	public Long getTime() {
		return this.time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Integer getAvailable() {
		return this.available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

}