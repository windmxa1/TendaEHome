package org.view;

/**
 * VGarouselId entity. @author MyEclipse Persistence Tools
 */

public class VGarouselId implements java.io.Serializable {

	// Fields

	private Long id;
	private String title;
	private String url;
	private Integer catalogId;
	private String hyperlink;
	private Long time;
	private String catalog;
	private String gerouselUrl;
	private String createTime;

	// Constructors

	/** default constructor */
	public VGarouselId() {
	}

	/** minimal constructor */
	public VGarouselId(Long id, String title, String url, Integer catalogId) {
		this.id = id;
		this.title = title;
		this.url = url;
		this.catalogId = catalogId;
	}

	/** full constructor */
	public VGarouselId(Long id, String title, String url, Integer catalogId,
			String hyperlink, Long time, String catalog, String gerouselUrl,
			String createTime) {
		this.id = id;
		this.title = title;
		this.url = url;
		this.catalogId = catalogId;
		this.hyperlink = hyperlink;
		this.time = time;
		this.catalog = catalog;
		this.gerouselUrl = gerouselUrl;
		this.createTime = createTime;
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

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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
				&& ((this.getTime() == castOther.getTime()) || (this.getTime() != null
						&& castOther.getTime() != null && this.getTime()
						.equals(castOther.getTime())))
				&& ((this.getCatalog() == castOther.getCatalog()) || (this
						.getCatalog() != null && castOther.getCatalog() != null && this
						.getCatalog().equals(castOther.getCatalog())))
				&& ((this.getGerouselUrl() == castOther.getGerouselUrl()) || (this
						.getGerouselUrl() != null
						&& castOther.getGerouselUrl() != null && this
						.getGerouselUrl().equals(castOther.getGerouselUrl())))
				&& ((this.getCreateTime() == castOther.getCreateTime()) || (this
						.getCreateTime() != null
						&& castOther.getCreateTime() != null && this
						.getCreateTime().equals(castOther.getCreateTime())));
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
		result = 37 * result
				+ (getTime() == null ? 0 : this.getTime().hashCode());
		result = 37 * result
				+ (getCatalog() == null ? 0 : this.getCatalog().hashCode());
		result = 37
				* result
				+ (getGerouselUrl() == null ? 0 : this.getGerouselUrl()
						.hashCode());
		result = 37
				* result
				+ (getCreateTime() == null ? 0 : this.getCreateTime()
						.hashCode());
		return result;
	}

}