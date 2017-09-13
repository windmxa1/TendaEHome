package org.view;

/**
 * VRepairOrder entity. @author MyEclipse Persistence Tools
 */

public class VRepairOrder implements java.io.Serializable {

	// Fields

	private VRepairOrderId id;

	// Constructors

	/** default constructor */
	public VRepairOrder() {
	}

	/** full constructor */
	public VRepairOrder(VRepairOrderId id) {
		this.id = id;
	}

	// Property accessors

	public VRepairOrderId getId() {
		return this.id;
	}

	public void setId(VRepairOrderId id) {
		this.id = id;
	}

}