package org.view;

/**
 * VAdmin entity. @author MyEclipse Persistence Tools
 */

public class VAdmin implements java.io.Serializable {

	// Fields

	private VAdminId id;

	// Constructors

	/** default constructor */
	public VAdmin() {
	}

	/** full constructor */
	public VAdmin(VAdminId id) {
		this.id = id;
	}

	// Property accessors

	public VAdminId getId() {
		return this.id;
	}

	public void setId(VAdminId id) {
		this.id = id;
	}

}