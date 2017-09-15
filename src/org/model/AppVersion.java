package org.model;

import java.sql.Timestamp;

/**
 * AppVersion entity. @author MyEclipse Persistence Tools
 */

public class AppVersion implements java.io.Serializable {

	// Fields

	private Integer id;
	private String version;
	private String publisher;
	private Timestamp time;
	private String downloadUrl;

	// Constructors

	/** default constructor */
	public AppVersion() {
	}

	/** minimal constructor */
	public AppVersion(String version, String publisher, String downloadUrl) {
		this.version = version;
		this.publisher = publisher;
		this.downloadUrl = downloadUrl;
	}

	/** full constructor */
	public AppVersion(String version, String publisher, Timestamp time,
			String downloadUrl) {
		this.version = version;
		this.publisher = publisher;
		this.time = time;
		this.downloadUrl = downloadUrl;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getDownloadUrl() {
		return this.downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

}