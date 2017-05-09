package org.view;

import java.math.BigDecimal;

/**
 * VUserId entity. @author MyEclipse Persistence Tools
 */

public class VUserId implements java.io.Serializable {

	// Fields

	private Long id;
	private String phone;
	private String password;
	private Long time;
	private String nickname;
	private String headUrl;
	private String createTime;
	private String url;
	private String address;
	private BigDecimal addressId;

	// Constructors

	/** default constructor */
	public VUserId() {
	}

	/** minimal constructor */
	public VUserId(Long id, String phone, String password, Long time) {
		this.id = id;
		this.phone = phone;
		this.password = password;
		this.time = time;
	}

	/** full constructor */
	public VUserId(Long id, String phone, String password, Long time,
			String nickname, String headUrl, String createTime, String url,
			String address, BigDecimal addressId) {
		this.id = id;
		this.phone = phone;
		this.password = password;
		this.time = time;
		this.nickname = nickname;
		this.headUrl = headUrl;
		this.createTime = createTime;
		this.url = url;
		this.address = address;
		this.addressId = addressId;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getTime() {
		return this.time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHeadUrl() {
		return this.headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getAddressId() {
		return this.addressId;
	}

	public void setAddressId(BigDecimal addressId) {
		this.addressId = addressId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VUserId))
			return false;
		VUserId castOther = (VUserId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getPhone() == castOther.getPhone()) || (this
						.getPhone() != null && castOther.getPhone() != null && this
						.getPhone().equals(castOther.getPhone())))
				&& ((this.getPassword() == castOther.getPassword()) || (this
						.getPassword() != null
						&& castOther.getPassword() != null && this
						.getPassword().equals(castOther.getPassword())))
				&& ((this.getTime() == castOther.getTime()) || (this.getTime() != null
						&& castOther.getTime() != null && this.getTime()
						.equals(castOther.getTime())))
				&& ((this.getNickname() == castOther.getNickname()) || (this
						.getNickname() != null
						&& castOther.getNickname() != null && this
						.getNickname().equals(castOther.getNickname())))
				&& ((this.getHeadUrl() == castOther.getHeadUrl()) || (this
						.getHeadUrl() != null && castOther.getHeadUrl() != null && this
						.getHeadUrl().equals(castOther.getHeadUrl())))
				&& ((this.getCreateTime() == castOther.getCreateTime()) || (this
						.getCreateTime() != null
						&& castOther.getCreateTime() != null && this
						.getCreateTime().equals(castOther.getCreateTime())))
				&& ((this.getUrl() == castOther.getUrl()) || (this.getUrl() != null
						&& castOther.getUrl() != null && this.getUrl().equals(
						castOther.getUrl())))
				&& ((this.getAddress() == castOther.getAddress()) || (this
						.getAddress() != null && castOther.getAddress() != null && this
						.getAddress().equals(castOther.getAddress())))
				&& ((this.getAddressId() == castOther.getAddressId()) || (this
						.getAddressId() != null
						&& castOther.getAddressId() != null && this
						.getAddressId().equals(castOther.getAddressId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getPhone() == null ? 0 : this.getPhone().hashCode());
		result = 37 * result
				+ (getPassword() == null ? 0 : this.getPassword().hashCode());
		result = 37 * result
				+ (getTime() == null ? 0 : this.getTime().hashCode());
		result = 37 * result
				+ (getNickname() == null ? 0 : this.getNickname().hashCode());
		result = 37 * result
				+ (getHeadUrl() == null ? 0 : this.getHeadUrl().hashCode());
		result = 37
				* result
				+ (getCreateTime() == null ? 0 : this.getCreateTime()
						.hashCode());
		result = 37 * result
				+ (getUrl() == null ? 0 : this.getUrl().hashCode());
		result = 37 * result
				+ (getAddress() == null ? 0 : this.getAddress().hashCode());
		result = 37 * result
				+ (getAddressId() == null ? 0 : this.getAddressId().hashCode());
		return result;
	}

}