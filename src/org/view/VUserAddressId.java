package org.view;

/**
 * VUserAddressId entity. @author MyEclipse Persistence Tools
 */

public class VUserAddressId implements java.io.Serializable {

	// Fields

	private Long userid;
	private String address;

	// Constructors

	/** default constructor */
	public VUserAddressId() {
	}

	/** minimal constructor */
	public VUserAddressId(Long userid) {
		this.userid = userid;
	}

	/** full constructor */
	public VUserAddressId(Long userid, String address) {
		this.userid = userid;
		this.address = address;
	}

	// Property accessors

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VUserAddressId))
			return false;
		VUserAddressId castOther = (VUserAddressId) other;

		return ((this.getUserid() == castOther.getUserid()) || (this
				.getUserid() != null && castOther.getUserid() != null && this
				.getUserid().equals(castOther.getUserid())))
				&& ((this.getAddress() == castOther.getAddress()) || (this
						.getAddress() != null && castOther.getAddress() != null && this
						.getAddress().equals(castOther.getAddress())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserid() == null ? 0 : this.getUserid().hashCode());
		result = 37 * result
				+ (getAddress() == null ? 0 : this.getAddress().hashCode());
		return result;
	}

}