package org.view;

/**
 * VUserAddress entity. @author MyEclipse Persistence Tools
 */

public class VUserAddress implements java.io.Serializable {

	// Fields

	private VUserAddressId id;

	// Constructors

	/** default constructor */
	public VUserAddress() {
	}

	/** full constructor */
	public VUserAddress(VUserAddressId id) {
		this.id = id;
	}

	// Property accessors

	public VUserAddressId getId() {
		return this.id;
	}

	public void setId(VUserAddressId id) {
		this.id = id;
	}

}