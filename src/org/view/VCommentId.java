package org.view;

import java.sql.Timestamp;

/**
 * VCommentId entity. @author MyEclipse Persistence Tools
 */

public class VCommentId implements java.io.Serializable {

	// Fields

	private Long id;
	private String description;
	private Integer rating;
	private Long userid;
	private Long orderId;
	private Integer isVisable;
	private Timestamp time;
	private String reply;
	private String headImg;
	private Integer catalogId;
	private String franchiseeName;
	private String nickname;
	private String imgUrl;

	// Constructors

	/** default constructor */
	public VCommentId() {
	}

	/** minimal constructor */
	public VCommentId(Long id, Long userid, Long orderId) {
		this.id = id;
		this.userid = userid;
		this.orderId = orderId;
	}

	/** full constructor */
	public VCommentId(Long id, String description, Integer rating, Long userid,
			Long orderId, Integer isVisable, Timestamp time, String reply,
			String headImg, Integer catalogId, String franchiseeName,
			String nickname, String imgUrl) {
		this.id = id;
		this.description = description;
		this.rating = rating;
		this.userid = userid;
		this.orderId = orderId;
		this.isVisable = isVisable;
		this.time = time;
		this.reply = reply;
		this.headImg = headImg;
		this.catalogId = catalogId;
		this.franchiseeName = franchiseeName;
		this.nickname = nickname;
		this.imgUrl = imgUrl;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getRating() {
		return this.rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Integer getIsVisable() {
		return this.isVisable;
	}

	public void setIsVisable(Integer isVisable) {
		this.isVisable = isVisable;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getReply() {
		return this.reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public String getHeadImg() {
		return this.headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public Integer getCatalogId() {
		return this.catalogId;
	}

	public void setCatalogId(Integer catalogId) {
		this.catalogId = catalogId;
	}

	public String getFranchiseeName() {
		return this.franchiseeName;
	}

	public void setFranchiseeName(String franchiseeName) {
		this.franchiseeName = franchiseeName;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VCommentId))
			return false;
		VCommentId castOther = (VCommentId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getDescription() == castOther.getDescription()) || (this
						.getDescription() != null
						&& castOther.getDescription() != null && this
						.getDescription().equals(castOther.getDescription())))
				&& ((this.getRating() == castOther.getRating()) || (this
						.getRating() != null && castOther.getRating() != null && this
						.getRating().equals(castOther.getRating())))
				&& ((this.getUserid() == castOther.getUserid()) || (this
						.getUserid() != null && castOther.getUserid() != null && this
						.getUserid().equals(castOther.getUserid())))
				&& ((this.getOrderId() == castOther.getOrderId()) || (this
						.getOrderId() != null && castOther.getOrderId() != null && this
						.getOrderId().equals(castOther.getOrderId())))
				&& ((this.getIsVisable() == castOther.getIsVisable()) || (this
						.getIsVisable() != null
						&& castOther.getIsVisable() != null && this
						.getIsVisable().equals(castOther.getIsVisable())))
				&& ((this.getTime() == castOther.getTime()) || (this.getTime() != null
						&& castOther.getTime() != null && this.getTime()
						.equals(castOther.getTime())))
				&& ((this.getReply() == castOther.getReply()) || (this
						.getReply() != null && castOther.getReply() != null && this
						.getReply().equals(castOther.getReply())))
				&& ((this.getHeadImg() == castOther.getHeadImg()) || (this
						.getHeadImg() != null && castOther.getHeadImg() != null && this
						.getHeadImg().equals(castOther.getHeadImg())))
				&& ((this.getCatalogId() == castOther.getCatalogId()) || (this
						.getCatalogId() != null
						&& castOther.getCatalogId() != null && this
						.getCatalogId().equals(castOther.getCatalogId())))
				&& ((this.getFranchiseeName() == castOther.getFranchiseeName()) || (this
						.getFranchiseeName() != null
						&& castOther.getFranchiseeName() != null && this
						.getFranchiseeName().equals(
								castOther.getFranchiseeName())))
				&& ((this.getNickname() == castOther.getNickname()) || (this
						.getNickname() != null
						&& castOther.getNickname() != null && this
						.getNickname().equals(castOther.getNickname())))
				&& ((this.getImgUrl() == castOther.getImgUrl()) || (this
						.getImgUrl() != null && castOther.getImgUrl() != null && this
						.getImgUrl().equals(castOther.getImgUrl())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37
				* result
				+ (getDescription() == null ? 0 : this.getDescription()
						.hashCode());
		result = 37 * result
				+ (getRating() == null ? 0 : this.getRating().hashCode());
		result = 37 * result
				+ (getUserid() == null ? 0 : this.getUserid().hashCode());
		result = 37 * result
				+ (getOrderId() == null ? 0 : this.getOrderId().hashCode());
		result = 37 * result
				+ (getIsVisable() == null ? 0 : this.getIsVisable().hashCode());
		result = 37 * result
				+ (getTime() == null ? 0 : this.getTime().hashCode());
		result = 37 * result
				+ (getReply() == null ? 0 : this.getReply().hashCode());
		result = 37 * result
				+ (getHeadImg() == null ? 0 : this.getHeadImg().hashCode());
		result = 37 * result
				+ (getCatalogId() == null ? 0 : this.getCatalogId().hashCode());
		result = 37
				* result
				+ (getFranchiseeName() == null ? 0 : this.getFranchiseeName()
						.hashCode());
		result = 37 * result
				+ (getNickname() == null ? 0 : this.getNickname().hashCode());
		result = 37 * result
				+ (getImgUrl() == null ? 0 : this.getImgUrl().hashCode());
		return result;
	}

}