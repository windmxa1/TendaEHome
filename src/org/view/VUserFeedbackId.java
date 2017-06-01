package org.view;

/**
 * VUserFeedbackId entity. @author MyEclipse Persistence Tools
 */

public class VUserFeedbackId implements java.io.Serializable {

	// Fields

	private Integer id;
	private String message;
	private Long userId;
	private Long time;
	private Short read;
	private String createTime;

	// Constructors

	/** default constructor */
	public VUserFeedbackId() {
	}

	/** minimal constructor */
	public VUserFeedbackId(Integer id, String message, Long userId, Long time,
			Short read) {
		this.id = id;
		this.message = message;
		this.userId = userId;
		this.time = time;
		this.read = read;
	}

	/** full constructor */
	public VUserFeedbackId(Integer id, String message, Long userId, Long time,
			Short read, String createTime) {
		this.id = id;
		this.message = message;
		this.userId = userId;
		this.time = time;
		this.read = read;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getTime() {
		return this.time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Short getRead() {
		return this.read;
	}

	public void setRead(Short read) {
		this.read = read;
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
		if (!(other instanceof VUserFeedbackId))
			return false;
		VUserFeedbackId castOther = (VUserFeedbackId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getMessage() == castOther.getMessage()) || (this
						.getMessage() != null && castOther.getMessage() != null && this
						.getMessage().equals(castOther.getMessage())))
				&& ((this.getUserId() == castOther.getUserId()) || (this
						.getUserId() != null && castOther.getUserId() != null && this
						.getUserId().equals(castOther.getUserId())))
				&& ((this.getTime() == castOther.getTime()) || (this.getTime() != null
						&& castOther.getTime() != null && this.getTime()
						.equals(castOther.getTime())))
				&& ((this.getRead() == castOther.getRead()) || (this.getRead() != null
						&& castOther.getRead() != null && this.getRead()
						.equals(castOther.getRead())))
				&& ((this.getCreateTime() == castOther.getCreateTime()) || (this
						.getCreateTime() != null
						&& castOther.getCreateTime() != null && this
						.getCreateTime().equals(castOther.getCreateTime())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getMessage() == null ? 0 : this.getMessage().hashCode());
		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result
				+ (getTime() == null ? 0 : this.getTime().hashCode());
		result = 37 * result
				+ (getRead() == null ? 0 : this.getRead().hashCode());
		result = 37
				* result
				+ (getCreateTime() == null ? 0 : this.getCreateTime()
						.hashCode());
		return result;
	}

}