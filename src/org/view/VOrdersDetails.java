package org.view;

/**
 * VOrdersDetails entity. @author MyEclipse Persistence Tools
 */

public class VOrdersDetails implements java.io.Serializable {

	// Fields

	private VOrdersDetailsId id;

	// Constructors

	/** default constructor */
	public VOrdersDetails() {
	}

	/** full constructor */
	public VOrdersDetails(VOrdersDetailsId id) {
		this.id = id;
	}

	// Property accessors

	public VOrdersDetailsId getId() {
		return this.id;
	}

	public void setId(VOrdersDetailsId id) {
		this.id = id;
	}

}