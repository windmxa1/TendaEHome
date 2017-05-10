package org.view;

import java.sql.Timestamp;

/**
 * VGarouselId entity. @author MyEclipse Persistence Tools
 */

public class VGarouselId implements java.io.Serializable {

	// Fields

	private Long id;
	private String title;
	private String url;
	private Long catalogId;
	private String hyperlink;
	private Timestamp createTime;
	private String catalog;
	private String gerouselUrl;

	// Constructors

	/** default constructor */
	public VGarouselId() {
	}

	/** minimal constructor */
	public VGarouselId(Long id, String title, String url, Long catalogId,
			Timestamp createTime) {
		this.id = id;
		this.title = title;
		this.url = url;
		this.catalogId = catalogId;
		this.createTime = createTime;
	}

	/** full constructor */
	public VGarouselId(Long id, String title, String url, Long catalogId,
			String hyperlink, Timestamp createTime, String catalog,
			String gerouselUrl) {
		this.id = id;
		this.title = title;
		this.url = url;
		this.catalogId = catalogId;
		this.hyperlink = hyperlink;
		this.createTime = createTime;
		this.catalog = catalog;
		this.gerouselUrl = gerouselUrl;
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

	public Long getCatalogId() {
		return this.catalogId;
	}

	public void setCatalogId(Long catalogId) {
		this.catalogId = catalogId;
	}

	public String getHyperlink() {
		return this.hyperlink;
	}

	public void setHyperlink(String hyperlink) {
		this.hyperlink = hyperlink;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getCatalog() {
		return this.catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public String getGerouselUrl() {
		return this.gerouselUrl;
	}

	public void setGerouselUrl(String gerouselUrl) {
		this.gerouselUrl = gerouselUrl;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VGarouselId))
			return false;
		VGarouselId castOther = (VGarouselId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getTitle() == castOther.getTitle()) || (this
						.getTitle() != null && castOther.getTitle() != null && this
						.getTitle().equals(castOther.getTitle())))
				&& ((this.getUrl() == castOther.getUrl()) || (this.getUrl() != null
						&& castOther.getUrl() != null && this.getUrl().equals(
						castOther.getUrl())))
				&& ((this.getCatalogId() == castOther.getCatalogId()) || (this
						.getCatalogId() != null
						&& castOther.getCatalogId() != null && this
						.getCatalogId().equals(castOther.getCatalogId())))
				&& ((this.getHyperlink() == castOther.getHyperlink()) || (this
						.getHyperlink() != null
						&& castOther.getHyperlink() != null && this
						.getHyperlink().equals(castOther.getHyperlink())))
				&& ((this.getCreateTime() == castOther.getCreateTime()) || (this
						.getCreateTime() != null
						&& castOther.getCreateTime() != null && this
						.getCreateTime().equals(castOther.getCreateTime())))
				&& ((this.getCatalog() == castOther.getCatalog()) || (this
						.getCatalog() != null && castOther.getCatalog() != null && this
						.getCatalog().equals(castOther.getCatalog())))
				&& ((this.getGerouselUrl() == castOther.getGerouselUrl()) || (this
						.getGerouselUrl() != null
						&& castOther.getGerouselUrl() != null && this
						.getGerouselUrl().equals(castOther.getGerouselUrl())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getTitle() == null ? 0 : this.getTitle().hashCode());
		result = 37 * result
				+ (getUrl() == null ? 0 : this.getUrl().hashCode());
		result = 37 * result
				+ (getCatalogId() == null ? 0 : this.getCatalogId().hashCode());
		result = 37 * result
				+ (getHyperlink() == null ? 0 : this.getHyperlink().hashCode());
		result = 37
				* result
				+ (getCreateTime() == null ? 0 : this.getCreateTime()
						.hashCode());
		result = 37 * result
				+ (getCatalog() == null ? 0 : this.getCatalog().hashCode());
		result = 37
				* result
				+ (getGerouselUrl() == null ? 0 : this.getGerouselUrl()
						.hashCode());
		return result;
	}

}