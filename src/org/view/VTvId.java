package org.view;

/**
 * VTvId entity. @author MyEclipse Persistence Tools
 */

public class VTvId implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String imgUrl;
	private String tvUrl;
	private Long time;
	private String createTime;
	private Integer available;

	// Constructors

	/** default constructor */
	public VTvId() {
	}

	/** minimal constructor */
	public VTvId(Integer id, String name, String tvUrl, Long time,
			Integer available) {
		this.id = id;
		this.name = name;
		this.tvUrl = tvUrl;
		this.time = time;
		this.available = available;
	}

	/** full constructor */
	public VTvId(Integer id, String name, String imgUrl, String tvUrl,
			Long time, String createTime, Integer available) {
		this.id = id;
		this.name = name;
		this.imgUrl = imgUrl;
		this.tvUrl = tvUrl;
		this.time = time;
		this.createTime = createTime;
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

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getAvailable() {
		return this.available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VTvId))
			return false;
		VTvId castOther = (VTvId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getName() == castOther.getName()) || (this.getName() != null
						&& castOther.getName() != null && this.getName()
						.equals(castOther.getName())))
				&& ((this.getImgUrl() == castOther.getImgUrl()) || (this
						.getImgUrl() != null && castOther.getImgUrl() != null && this
						.getImgUrl().equals(castOther.getImgUrl())))
				&& ((this.getTvUrl() == castOther.getTvUrl()) || (this
						.getTvUrl() != null && castOther.getTvUrl() != null && this
						.getTvUrl().equals(castOther.getTvUrl())))
				&& ((this.getTime() == castOther.getTime()) || (this.getTime() != null
						&& castOther.getTime() != null && this.getTime()
						.equals(castOther.getTime())))
				&& ((this.getCreateTime() == castOther.getCreateTime()) || (this
						.getCreateTime() != null
						&& castOther.getCreateTime() != null && this
						.getCreateTime().equals(castOther.getCreateTime())))
				&& ((this.getAvailable() == castOther.getAvailable()) || (this
						.getAvailable() != null
						&& castOther.getAvailable() != null && this
						.getAvailable().equals(castOther.getAvailable())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		result = 37 * result
				+ (getImgUrl() == null ? 0 : this.getImgUrl().hashCode());
		result = 37 * result
				+ (getTvUrl() == null ? 0 : this.getTvUrl().hashCode());
		result = 37 * result
				+ (getTime() == null ? 0 : this.getTime().hashCode());
		result = 37
				* result
				+ (getCreateTime() == null ? 0 : this.getCreateTime()
						.hashCode());
		result = 37 * result
				+ (getAvailable() == null ? 0 : this.getAvailable().hashCode());
		return result;
	}

}