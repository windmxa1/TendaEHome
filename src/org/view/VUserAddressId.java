package org.view;

/**
 * VUserAddressId entity. @author MyEclipse Persistence Tools
 */

public class VUserAddressId implements java.io.Serializable {

	// Fields

	private Long userid;
	private Long id;
	private String address;

	// Constructors

	/** default constructor */
	public VUserAddressId() {
	}

	/** minimal constructor */
	public VUserAddressId(Long userid, Long id) {
		this.userid = userid;
		this.id = id;
	}

	/** full constructor */
	public VUserAddressId(Long userid, Long id, String address) {
		this.userid = userid;
		this.id = id;
		this.address = address;
	}

	// Property accessors

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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
				&& ((this.getId() == castOther.getId()) || (this.getId() != null
						&& castOther.getId() != null && this.getId().equals(
						castOther.getId())))
				&& ((this.getAddress() == castOther.getAddress()) || (this
						.getAddress() != null && castOther.getAddress() != null && this
						.getAddress().equals(castOther.getAddress())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserid() == null ? 0 : this.getUserid().hashCode());
		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getAddress() == null ? 0 : this.getAddress().hashCode());
		return result;
	}

}